package com.zerotoismail.account.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDto {
    private String statusCode;
    private String message;

//    public ResponseDto(String statusCode, String message) {
//        this.statusCode = statusCode;
//        this.message = message;
//    }
}
