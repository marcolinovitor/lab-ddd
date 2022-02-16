package br.com.lab.impacta.account.domain.service.impl;

import br.com.lab.impacta.account.domain.exception.AccountNotFoundException;
import br.com.lab.impacta.account.domain.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.domain.model.Account;
import br.com.lab.impacta.account.domain.service.AccountService;
import br.com.lab.impacta.account.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

// essa implementação está para Domain Layer Service na arq hexagonal
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${lab.account.exceptions.account-not-found-message}")
    private String messageExceptionAccountNotFound;
    @Value("${lab.account.exceptions.account-not-found-description}")
    private String descriptionExceptionAccountNotFound;


    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccWithoutBalance;
    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccWithoutBalance;

    @Override
    public Account find(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty()) {
            throw new AccountNotFoundException(
                    messageExceptionAccountNotFound,
                    descriptionExceptionAccountNotFound
            );
        }
        return account.get();
    }

    @Override
    public void debit(Long accountId, Double valueOfDebit) {
        Account account = this.find(accountId);

        boolean debited = account.debit(valueOfDebit);

        if (!debited) {
            throw new AccountWithoutBalanceException(
                    messageExceptionAccWithoutBalance,
                    descriptionExceptionAccWithoutBalance
            );
        }
        accountRepository.save(account);
    }
}
