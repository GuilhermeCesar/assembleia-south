package br.com.south.repository;

import br.com.south.entity.Associado;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssociadoRepository extends PagingAndSortingRepository<Associado, Long> , JpaSpecificationExecutor<Associado> {



}
