package br.com.lab.impacta.account.infrastructure.repository;

import br.com.lab.impacta.account.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// essa interface está para um Ports de Repositorio de dados
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
