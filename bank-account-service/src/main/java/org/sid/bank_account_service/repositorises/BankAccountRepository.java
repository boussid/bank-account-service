package org.sid.bank_account_service.repositorises;

import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
