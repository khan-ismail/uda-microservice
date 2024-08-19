package com.zerotoismail.account.service.impl;

import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.repository.AccountsRepository;
import com.zerotoismail.account.repository.CustomerRepository;
import com.zerotoismail.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
