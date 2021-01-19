package br.com.south.resource.v1;

import br.com.south.config.SwaggerConfig;
import br.com.south.core.dto.CadastrarSessaoDTO;
import br.com.south.core.dto.SessaoDTO;
import br.com.south.core.service.SessaoService;
import br.com.south.dto.ErrorMessage;
import br.com.south.dto.VotoDTO;
import br.com.south.dto.VotoFinalizadoDTO;
import br.com.south.service.VotoService;
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
@RequestMapping(path = "/v1/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.SESSAO)
public class SessaoResource {

    private final SessaoService sessaoService;
    private final VotoService votoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "${api.sessao.criacao}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = SessaoDTO.class),
            @ApiResponse(code = 500, message = "Falha ao inserir sessão", response = ErrorMessage.class)
    })
    @PostMapping
    public SessaoDTO create(@Valid @RequestBody CadastrarSessaoDTO sessaoDTO) {
        return this.sessaoService.criarSessao(sessaoDTO);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = SessaoDTO.class),
            @ApiResponse(code = 404, message = "Sessão não encontrada", response = ErrorMessage.class)
    })
    @GetMapping(path = "/{idSessao}")
    @ApiOperation(value = "${api.sessao.busca}")
    @ResponseStatus(OK)
    public SessaoDTO get(@PathVariable("idSessao") Long idSessao) {
        return this.sessaoService.obterSessao(idSessao);
    }

    @GetMapping(path = "/busca")
    @ApiOperation(value = "${api.sessao.busca}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = Page.class),
            @ApiResponse(code = 404, message = "Sessão não encontrada", response = ErrorMessage.class)
    })
    @ResponseStatus(OK)
    public Page<SessaoDTO> findBy(@RequestParam(value = "pauta", required = false) String pauta,
                                  @RequestParam(value = "encerrada", required = false) Boolean encerrada,
                                  Pageable pageable) {
        return this.sessaoService.buscarSessao(pauta, encerrada, pageable);
    }

    @ApiOperation(value = "${api.sessao.votar}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Sucesso", response = VotoFinalizadoDTO.class),
            @ApiResponse(code = 404, message = "Sessão não encontrada", response = ErrorMessage.class)
    })
    @PostMapping("/{idSessao}/voto")
    @ResponseStatus(CREATED)
    public VotoFinalizadoDTO votar(@PathVariable("idSessao") Long idSessao, @RequestBody VotoDTO votoDTO) {
        return this.votoService.votar(idSessao, votoDTO);
    }

    @ApiOperation(value = "${api.sessao.buscar.voto}", response = VotoFinalizadoDTO.class)
    @GetMapping("/{idSessao}/voto/contagem")
    @ResponseStatus(OK)
    public VotoFinalizadoDTO votar(@PathVariable("idSessao") Long idSessao) {
        return this.votoService.contagemVotos(idSessao);
    }
}
