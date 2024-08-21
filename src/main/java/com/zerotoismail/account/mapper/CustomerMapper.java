package com.zerotoismail.account.mapper;

import com.zerotoismail.account.dto.CustomerDto;
import com.zerotoismail.account.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setName(customerEntity.getName());
        customerDto.setMobileNumber(customerEntity.getMobileNumber());
        return customerDto;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setName(customerDto.getName());
        customerEntity.setMobileNumber(customerDto.getMobileNumber());
        return customerEntity;
    }
}
