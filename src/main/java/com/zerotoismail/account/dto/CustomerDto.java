package com.zerotoismail.account.dto;

import com.zerotoismail.account.entity.AccountsEntity;
import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountDto account;
}
