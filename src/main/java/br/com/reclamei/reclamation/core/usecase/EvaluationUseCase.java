package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.EvaluationDomain;
import br.com.reclamei.reclamation.core.gateway.EvaluationGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record EvaluationUseCase(EvaluationGateway gateway) {

    public void save(EvaluationDomain domain) {
        log.info("[EvaluationUseCase] :: create :: Creating new evaluation. {}", domain);
        gateway.save(domain);
    }

}
