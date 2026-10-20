package org.sid.bank_account_service.projections;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "accountSummary", types = {BankAccount.class})
public interface BankAccountProjection {
    String getId();
    Double getBalance();
    String getCurrency();
    AccountType getType();
}
