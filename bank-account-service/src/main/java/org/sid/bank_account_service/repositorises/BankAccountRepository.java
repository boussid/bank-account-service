package org.sid.bank_account_service.repositorises;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.projections.BankAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "accounts", excerptProjection = BankAccountProjection.class)
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
