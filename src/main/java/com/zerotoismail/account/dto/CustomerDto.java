package com.zerotoismail.account.dto;

import com.zerotoismail.account.entity.AccountsEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold customer and account information")
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Ismail Khan")
    @NotEmpty(message="Name can not be a null or empty")
    @Size(min=2, message="Name must be minimum 2 character long")
    private String name;

    @Schema(description = "Email of the customer", example = "Ismail@gmail.com")
    @NotEmpty(message="Email can not be a null or empty")
    @Email(message="Email address should be a valid value")
    private String email;

    @Schema(description = "Mobile Number of the customer",example = "8898021335")
    @NotEmpty(message="Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountDto account;
}
