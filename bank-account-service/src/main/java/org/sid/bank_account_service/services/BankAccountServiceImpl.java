package org.sid.bank_account_service.services;

import jakarta.transaction.Transactional;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.BankAccountMapper;
import org.sid.bank_account_service.repositorises.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        return bankAccountMapper.toDTO(account);
    }

    @Override
    public BankAccountResponseDTO createAccount(BankAccountRequestDTO dto) {
        BankAccount account = bankAccountMapper.fromDTO(dto);
        account.setId(UUID.randomUUID().toString());
        account.setCreateAt(new Date());
        return bankAccountMapper.toDTO(bankAccountRepository.save(account));
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO dto) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        if (dto.getBalance() != null) account.setBalance(dto.getBalance());
        if (dto.getCurrency() != null) account.setCurrency(dto.getCurrency());
        if (dto.getType() != null) account.setType(dto.getType());
        return bankAccountMapper.toDTO(bankAccountRepository.save(account));
    }

    @Override
    public void deleteAccount(String id) {
        if (!bankAccountRepository.existsById(id))
            throw new RuntimeException(String.format("Compte %s introuvable", id));
        bankAccountRepository.deleteById(id);
    }
}
