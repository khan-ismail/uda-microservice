package com.zerotoismail.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold account information")
public class AccountDto {

    @Schema(description = "Account Number of the bank", example = "2319981903")
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account Type of the bank",example = "SAVING")
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(description = "Branch Address of the bank")
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;
}
