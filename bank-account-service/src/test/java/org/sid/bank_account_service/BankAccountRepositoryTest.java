package org.sid.bank_account_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositorises.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount savedAccount;

    @BeforeEach
    void setUp() {
        bankAccountRepository.deleteAll();
        savedAccount = bankAccountRepository.save(BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(50000.0)
                .currency("MAD")
                .type(AccountType.CURRENT_ACCOUNT)
                .createAt(new Date())
                .build());
    }

    @Test
    void testFindAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        assertThat(accounts).isNotEmpty();
        assertThat(accounts).hasSize(1);
    }

    @Test
    void testSaveAccount() {
        BankAccount account = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(120000.0)
                .currency("EUR")
                .type(AccountType.SAVING_ACCOUNT)
                .createAt(new Date())
                .build();
        BankAccount result = bankAccountRepository.save(account);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getBalance()).isEqualTo(120000.0);
        assertThat(result.getCurrency()).isEqualTo("EUR");
        assertThat(result.getType()).isEqualTo(AccountType.SAVING_ACCOUNT);
    }

    @Test
    void testFindById_found() {
        Optional<BankAccount> found = bankAccountRepository.findById(savedAccount.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(savedAccount.getId());
        assertThat(found.get().getBalance()).isEqualTo(50000.0);
    }

    @Test
    void testFindById_notFound() {
        Optional<BankAccount> found = bankAccountRepository.findById("id-inexistant");
        assertThat(found).isEmpty();
    }

    @Test
    void testUpdateAccount() {
        savedAccount.setBalance(99999.0);
        savedAccount.setCurrency("USD");
        BankAccount updated = bankAccountRepository.save(savedAccount);
        assertThat(updated.getBalance()).isEqualTo(99999.0);
        assertThat(updated.getCurrency()).isEqualTo("USD");
    }

    @Test
    void testDeleteById() {
        bankAccountRepository.deleteById(savedAccount.getId());
        Optional<BankAccount> found = bankAccountRepository.findById(savedAccount.getId());
        assertThat(found).isEmpty();
        assertThat(bankAccountRepository.findAll()).isEmpty();
    }

    @Test
    void testCountAccounts() {
        long count = bankAccountRepository.count();
        assertThat(count).isEqualTo(1);
    }
}
