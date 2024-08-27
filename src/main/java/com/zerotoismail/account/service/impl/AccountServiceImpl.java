package com.zerotoismail.account.service.impl;

import com.zerotoismail.account.constant.AccountConstants;
import com.zerotoismail.account.dto.AccountDto;
import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.entity.AccountsEntity;
import com.zerotoismail.account.entity.CustomerEntity;
import com.zerotoismail.account.exception.CustomerAlreadyException;
import com.zerotoismail.account.exception.ResourcesNotFoundException;
import com.zerotoismail.account.mapper.AccountsMapper;
import com.zerotoismail.account.mapper.CustomerMapper;
import com.zerotoismail.account.repository.AccountsRepository;
import com.zerotoismail.account.repository.CustomerRepository;
import com.zerotoismail.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDto, new CustomerEntity());

        Optional<CustomerEntity> optionCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionCustomer.isPresent()) {
            throw new CustomerAlreadyException("Customer already register with given mobileNumber " + customerDto.getMobileNumber());
        }

        customerEntity.setCreatedAt(LocalDateTime.now());
        customerEntity.setCreatedBy("Anonymous");
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        accountsRepository.save(
                createCustomerAccount(savedCustomer.getCustomerId()));
    }


    private AccountsEntity createCustomerAccount(long customerId) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setCustomerId(customerId);
        long accountNumber = 1000000000 + new Random().nextLong(900000000);
        accountsEntity.setAccountNumber(accountNumber);
        accountsEntity.setAccountType(AccountConstants.SAVINGS);
        accountsEntity.setBranchAddress(AccountConstants.ADDRESS);

        accountsEntity.setCreatedAt(LocalDateTime.now());
        accountsEntity.setCreatedBy("Anonymous");

        return accountsEntity;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourcesNotFoundException("Customer", "mobileNumber", mobileNumber));

        AccountsEntity accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourcesNotFoundException("Account", "Customer Id", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccount(AccountsMapper.mapToAccountDto(accounts, new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        AccountDto accountDto = customerDto.getAccount();

        if(accountDto != null) {
            AccountsEntity accounts = accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourcesNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccountsEntity(accountDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourcesNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.mapToCustomerEntity(customerDto, customer);
            customer.setEmail(customerDto.getEmail());
            customer.setName(customerDto.getName());
            customer.setMobileNumber(customerDto.getMobileNumber());
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;


    }
}
