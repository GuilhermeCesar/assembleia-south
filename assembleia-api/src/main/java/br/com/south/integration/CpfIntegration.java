package br.com.south.integration;

import br.com.south.dto.CpfValidatorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpfIntegration",
        url = "${api.base}")
public interface CpfIntegration {

    @GetMapping(path = "${api.cpf}")
    CpfValidatorDTO buscarCpfElegivel(@PathVariable("cpf") String cpf);
}
