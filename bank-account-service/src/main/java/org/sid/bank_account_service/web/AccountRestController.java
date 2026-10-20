package org.sid.bank_account_service.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Bank Accounts", description = "API de gestion des comptes bancaires")
public class AccountRestController {

    private final BankAccountService bankAccountService;

    public AccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/bankAccounts")
    @Operation(summary = "Lister tous les comptes", description = "Retourne la liste de tous les comptes bancaires")
    public List<BankAccountResponseDTO> bankAccounts() {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    @Operation(summary = "Obtenir un compte par ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Compte trouvé"),
        @ApiResponse(responseCode = "404", description = "Compte introuvable")
    })
    public BankAccountResponseDTO bankAccount(
            @Parameter(description = "Identifiant du compte") @PathVariable String id) {
        return bankAccountService.getAccountById(id);
    }

    @PostMapping("/bankAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau compte")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO dto) {
        return bankAccountService.createAccount(dto);
    }

    @PutMapping("/bankAccounts/{id}")
    @Operation(summary = "Mettre à jour un compte existant")
    public BankAccountResponseDTO update(
            @Parameter(description = "Identifiant du compte") @PathVariable String id,
            @RequestBody BankAccountRequestDTO dto) {
        return bankAccountService.updateAccount(id, dto);
    }

    @DeleteMapping("/bankAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un compte")
    public void deleteAccount(
            @Parameter(description = "Identifiant du compte") @PathVariable String id) {
        bankAccountService.deleteAccount(id);
    }
}
