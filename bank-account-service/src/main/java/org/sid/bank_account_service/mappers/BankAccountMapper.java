package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccount fromDTO(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .balance(dto.getBalance())
                .currency(dto.getCurrency())
                .type(dto.getType())
                .build();
    }

    public BankAccountResponseDTO toDTO(BankAccount account) {
        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .createdAt(account.getCreateAt())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .type(account.getType())
                .build();
    }
}
