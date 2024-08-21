package com.zerotoismail.account.repository;

import com.zerotoismail.account.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {

    Optional<AccountsEntity> findByCustomerId(long customerId);
}
