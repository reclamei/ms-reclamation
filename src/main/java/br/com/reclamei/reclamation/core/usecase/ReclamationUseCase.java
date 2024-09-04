package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.DashboardDomain;
import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.gateway.CompanyGateway;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.*;

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
        return gateway.findAllByCompany(companyFilterDomains)
                .stream()
                .map(this::fillServiceProperties)
                .toList();
    }

    public DashboardDomain buildDashboardByCompany(Map<Long, List<Long>> companyFilterDomains) {
        log.info("[ReclamationUseCase] :: buildDashboardByCompany :: Building dashboard by company");
        final var reclamations = gateway.findAllByCompany(companyFilterDomains)
            .stream()
            .map(this::fillServiceProperties)
            .toList();
        final var totalCount = (long) reclamations.size();
        final var responseCount = reclamations.stream().filter(item -> !List.of(OPEN, IN_ANALYSIS).contains(item.getStatus())).count();
        final var unansweredCount = totalCount - responseCount;
        final var resolvedCount = reclamations.stream().filter(item -> List.of(REJECTED, UNIDENTIFIED, RESOLVED).contains(item.getStatus())).count();
        final var unresolvedCount = totalCount - resolvedCount;

        return new DashboardDomain(totalCount, responseCount, unansweredCount, resolvedCount, unresolvedCount);
    }
}
