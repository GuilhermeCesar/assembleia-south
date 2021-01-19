package br.com.south.core.repository;

import br.com.south.core.entity.Sessao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SessaoRepository extends PagingAndSortingRepository<Sessao, Long>, JpaSpecificationExecutor<Sessao> {

    Page<Sessao> findByEncerrada(Boolean encerrada, Pageable pageable);
}
