package br.com.south.resource.v1;

import br.com.south.config.SwaggerConfig;
import br.com.south.dto.AssociadoDTO;
import br.com.south.dto.CadastrarAssociadoDTO;
import br.com.south.dto.ErrorMessage;
import br.com.south.core.dto.SessaoDTO;
import br.com.south.service.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/associado", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.ASSOCIADO)
public class AssociadoResource {

    private final AssociadoService associadoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "${api.associado.criacao}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = SessaoDTO.class),
            @ApiResponse(code = 500, message = "Falha ao inserir associado", response = ErrorMessage.class)
    })
    @PostMapping
    public AssociadoDTO create(@Valid @RequestBody CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        return this.associadoService.cadastrarAssociado(cadastrarAssociadoDTO);
    }

    @GetMapping(path = "/busca")
    @ApiOperation(value = "${api.associado.filtro}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = Page.class),
            @ApiResponse(code = 500, message = "Falha ao inserir associado", response = ErrorMessage.class)
    })
    @ResponseStatus(OK)
    public Page<AssociadoDTO> findBy(@RequestParam(value = "cpf", required = false) String cpf,
                                     @RequestParam(value = "idSessao", required = false) Long idSessao,
                                     @RequestParam(value = "nome", required = false) String nome,
                                     Pageable pageable) {
        return this.associadoService.findSessao(cpf, idSessao, nome, pageable);
    }
}
