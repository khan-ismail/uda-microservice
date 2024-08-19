package com.zerotoismail.account.controller;

import com.zerotoismail.account.constant.AccountConstants;
import com.zerotoismail.account.dto.AccountDto;
import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.dto.ResponseDto;
import com.zerotoismail.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
}
