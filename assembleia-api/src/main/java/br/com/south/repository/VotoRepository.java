package br.com.south.repository;

import br.com.south.dto.ContagemVotacaoDTO;
import br.com.south.entity.Associado;
import br.com.south.entity.Voto;
import br.com.south.entity.VotoId;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends PagingAndSortingRepository<Voto, VotoId>, JpaSpecificationExecutor<Associado> {

    @Query("""
            select new br.com.south.dto.ContagemVotacaoDTO(voto.aprovado, COUNT(voto.aprovado))
            FROM Voto as voto            
            WHERE voto.sessaoId = :idSessao
            GROUP BY voto.aprovado
            """)
    List<ContagemVotacaoDTO> contaVotacao(@Param("idSessao") Long idSessao);
}
