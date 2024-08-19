package com.zerotoismail.account.service.impl;

import com.zerotoismail.account.constant.AccountConstants;
import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.entity.AccountsEntity;
import com.zerotoismail.account.entity.CustomerEntity;
import com.zerotoismail.account.exception.CustomerAlreadyException;
import com.zerotoismail.account.mapper.CustomerMapper;
import com.zerotoismail.account.repository.AccountsRepository;
import com.zerotoismail.account.repository.CustomerRepository;
import com.zerotoismail.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDto);

        Optional<CustomerEntity> optionCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionCustomer.isPresent()) {
            throw new CustomerAlreadyException("Customer already register with given mobileNumber " + customerDto.getMobileNumber());
        }

        customerEntity.setCreatedAt(LocalDateTime.now());
        customerEntity.setCreatedBy("Anonymous");
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        accountsRepository.save(
        createCustomerAccount(savedCustomer.getCustomerId()));
    }

    private AccountsEntity createCustomerAccount(long customerId){
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
}
