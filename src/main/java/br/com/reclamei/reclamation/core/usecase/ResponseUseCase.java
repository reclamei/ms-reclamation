package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;
import br.com.reclamei.reclamation.core.gateway.ResponseGateway;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public record ResponseUseCase(ResponseGateway gateway) {

    public void save(ResponseDomain domain) {
        log.info("[ResponseUseCase] :: create :: Creating new response. {}", domain);
        gateway.save(domain);
    }

    public List<ResponseDomain> findByReclamation(Long reclamationId) {
        log.info("[ResponseUseCase] :: findByCitizen :: Finding response with reclamation_id {}", reclamationId);
        return gateway.findByReclamation(reclamationId);
    }

    public void deleteById(Long id) {
        log.info("[ResponseUseCase] :: deleteById :: Deleting response with id {}", id);
        gateway.deleteById(id);
    }

}
