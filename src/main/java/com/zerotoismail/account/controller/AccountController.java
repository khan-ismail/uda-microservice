package com.zerotoismail.account.controller;

import com.zerotoismail.account.constant.AccountConstants;
import com.zerotoismail.account.dto.AccountDto;
import com.zerotoismail.account.dto.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody AccountDto accountDto){

        ResponseDto dto = new ResponseDto();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto());
    }
}
