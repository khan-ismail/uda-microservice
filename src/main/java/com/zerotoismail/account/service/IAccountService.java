package com.zerotoismail.account.service;

import com.zerotoismail.account.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
