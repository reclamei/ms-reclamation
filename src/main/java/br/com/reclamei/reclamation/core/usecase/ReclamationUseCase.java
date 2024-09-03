package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.gateway.CompanyGateway;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public record ReclamationUseCase(ReclamationGateway gateway, CompanyGateway companyGateway) {

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
        return gateway.findByCompany(serviceSubtypeId, locationId)
            .stream()
            .map(this::fillServiceProperties)
            .toList();
    }

    public List<ReclamationDomain> findByCitizen(Long citizenId) {
        log.info("[ReclamationUseCase] :: findByCitizen :: Finding company with citizen_id {}", citizenId);
        return gateway.findByCitizen(citizenId);
    }

    public void deleteById(Long id) {
        log.info("[ReclamationUseCase] :: deleteById :: Deleting reclamation with id {}", id);
        gateway.deleteById(id);
    }

    private ReclamationDomain fillServiceProperties(final ReclamationDomain reclamation) {
        var serviceSubtype = companyGateway.getServiceSubtype(reclamation.getServiceSubtypeId());
        reclamation.setServiceSubtypeName(serviceSubtype.getName());
        reclamation.setServiceName(serviceSubtype.getServiceName());

        return reclamation;
    }

    public List<ReclamationDomain> findAllByCompany(Map<Long, List<Long>> companyFilterDomains) {
        log.info("[ReclamationUseCase] :: findAllByCompany :: Finding all reclamations by company");
        return gateway.findAllByCompany(companyFilterDomains);
    }
}
