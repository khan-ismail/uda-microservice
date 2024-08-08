package com.zerotoismail.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountsEntity extends BaseEntity {

    @Id
    private int accountNumber;
    private Long customerId;
    private String accountType;
    private String branchName;
}
