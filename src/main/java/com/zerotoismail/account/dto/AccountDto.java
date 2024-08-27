package com.zerotoismail.account.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;
}
