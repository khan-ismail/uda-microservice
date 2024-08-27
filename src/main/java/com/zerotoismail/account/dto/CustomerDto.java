package com.zerotoismail.account.dto;

import com.zerotoismail.account.entity.AccountsEntity;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message="Name can not be a null or empty")
    @Size(min=2, message="Name must be minimum 2 character long")
    private String name;

    @NotEmpty(message="Email can not be a null or empty")
    @Email(message="Email address should be a valid value")
    private String email;

    @NotEmpty(message="Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10")
    private String mobileNumber;

    private AccountDto account;
}
