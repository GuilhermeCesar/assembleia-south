package br.com.south.core.repository;

import br.com.south.core.dto.ContagemVotacaoDTO;
import br.com.south.core.entity.Associado;
import br.com.south.core.entity.Voto;
import br.com.south.core.entity.VotoId;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends PagingAndSortingRepository<Voto, VotoId>, JpaSpecificationExecutor<Associado> {

    @Query("""
            select new br.com.south.core.dto.ContagemVotacaoDTO(voto.aprovado, COUNT(voto.aprovado))
            FROM Voto as voto            
            WHERE voto.sessaoId = :idSessao
            GROUP BY voto.aprovado
            """)
    List<ContagemVotacaoDTO> contaVotacao(@Param("idSessao") Long idSessao);
}
