package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public record ReclamationUseCase(ReclamationGateway gateway) {

    public void save(ReclamationDomain domain) {
        log.info("[ReclamationUseCase] :: create :: Creating new reclamation. {}", domain);
        gateway.save(domain);
    }

    public void updateStatus(Long id, ReclamationStatusType status) {
        log.info("[ReclamationUseCase] :: updateStatus :: Updating reclamation {} to {}", id, status);
        gateway.updateStatus(id, status);
    }

    public List<ReclamationDomain> findByCompany(Long serviceSubtypeId, Long locationId) {
        log.info("[ReclamationUseCase] :: findByCompany :: Finding reclamation with [service_subtype_id: {}, location_id: {}]", serviceSubtypeId, locationId);
        return gateway.findByCompany(serviceSubtypeId, locationId);
    }

    public List<ReclamationDomain> findByCitizen(Long citizenId) {
        log.info("[ReclamationUseCase] :: findByCitizen :: Finding company with citizen_id {}", citizenId);
        return gateway.findByCitizen(citizenId);
    }

    public void deleteById(Long id) {
        log.info("[ReclamationUseCase] :: deleteById :: Deleting reclamation with id {}", id);
        gateway.deleteById(id);
    }
}
