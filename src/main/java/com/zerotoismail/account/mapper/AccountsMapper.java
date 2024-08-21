package com.zerotoismail.account.mapper;

import com.zerotoismail.account.dto.AccountDto;
import com.zerotoismail.account.entity.AccountsEntity;

public class AccountsMapper {

    public static AccountDto mapToAccountDto(AccountsEntity accountsEntity) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(accountsEntity.getAccountNumber());
        accountDto.setAccountType(accountsEntity.getAccountType());
        accountDto.setBranchAddress(accountsEntity.getBranchAddress());

        return accountDto;
    }

    public static AccountsEntity mapToAccountsEntity(AccountDto accountDto) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setCustomerId(accountDto.getAccountNumber());
        accountsEntity.setAccountType(accountDto.getAccountType());
        accountsEntity.setAccountNumber(accountDto.getAccountNumber());
        accountsEntity.setAccountType(accountDto.getAccountType());
        accountsEntity.setBranchAddress(accountDto.getBranchAddress());
        return accountsEntity;
    }
}
