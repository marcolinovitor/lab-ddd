package br.com.lab.impacta.account.application.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebitAccountResponse {
    private boolean debited;

    public DebitAccountResponse(boolean debited) {
        this.debited = debited;
    }
}
