package org.sid.bank_account_service.services;

import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;

import java.util.List;

public interface BankAccountService {
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO getAccountById(String id);
    BankAccountResponseDTO createAccount(BankAccountRequestDTO dto);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO dto);
    void deleteAccount(String id);
}
