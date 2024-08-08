package com.zerotoismail.account.repository;

import com.zerotoismail.account.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {
}
