package br.com.south.core.repository;

import br.com.south.core.entity.Sessao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessaoRepository extends PagingAndSortingRepository<Sessao, Long>, JpaSpecificationExecutor<Sessao> {
}