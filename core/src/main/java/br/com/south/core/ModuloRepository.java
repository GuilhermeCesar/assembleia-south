package br.com.south.core;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "br.com.south.core.entity")
@EnableJpaRepositories(basePackages = "br.com.south.core.repository")
@ComponentScan(basePackages = "br.com.south.core.service")
public class ModuloRepository {
}
