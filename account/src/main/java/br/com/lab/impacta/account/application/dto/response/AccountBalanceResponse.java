package br.com.lab.impacta.account.application.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountBalanceResponse {
    private Long accountId;
    private Double balance;

    public AccountBalanceResponse(Long accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}
