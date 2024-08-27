package com.zerotoismail.account.controller;

import com.zerotoismail.account.constant.AccountConstants;
import com.zerotoismail.account.dto.AccountDto;
import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.dto.ErrorResponseDto;
import com.zerotoismail.account.dto.ResponseDto;
import com.zerotoismail.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Account CRUD REST API",
    description = "CRUD REST API for CREATE, READ, UPDATE and Delete"
)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer & Account details based on mobile number"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @NotEmpty(message="Mobile number can not be null or empty") @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        System.out.println("customerDto: " + customerDto.toString());
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Customer & Account details REST API",
            description = "REST API to update Customer & Account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Update Customer & Account details"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Customer & Account details REST API",
            description = "REST API to delete Customer & Account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete Customer & Account details"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam  @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number")  String mobileNumber){
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
    }
}
