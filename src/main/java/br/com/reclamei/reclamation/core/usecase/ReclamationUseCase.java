package br.com.reclamei.reclamation.core.usecase;

import br.com.reclamei.reclamation.core.domain.DashboardDomain;
import br.com.reclamei.reclamation.core.domain.HeatmapDataDomain;
import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.domain.ReportsDomain;
import br.com.reclamei.reclamation.core.gateway.CompanyGateway;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.IN_ANALYSIS;
import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.OPEN;
import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.REJECTED;
import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.RESOLVED;
import static br.com.reclamei.reclamation.core.type.ReclamationStatusType.UNIDENTIFIED;

@Slf4j
public record ReclamationUseCase(ReclamationGateway gateway, CompanyGateway companyGateway) {

    public void save(final ReclamationDomain domain) {
        log.info("[ReclamationUseCase] :: create :: Creating new reclamation. {}", domain);
        gateway.save(domain);
    }

    public void updateStatus(final Long id, final ReclamationStatusType status) {
        log.info("[ReclamationUseCase] :: updateStatus :: Updating reclamation {} to {}", id, status);
        gateway.updateStatus(id, status);
    }

    public List<ReclamationDomain> findByCitizen(final Long citizenId) {
        log.info("[ReclamationUseCase] :: findByCitizen :: Finding company with citizen_id {}", citizenId);
        return gateway.findByCitizen(citizenId);
    }

    public void deleteById(final Long id) {
        log.info("[ReclamationUseCase] :: deleteById :: Deleting reclamation with id {}", id);
        gateway.deleteById(id);
    }

    private ReclamationDomain fillServiceProperties(final ReclamationDomain reclamation) {
        var serviceSubtype = companyGateway.getServiceSubtype(reclamation.getServiceSubtypeId());
        reclamation.setServiceSubtypeName(serviceSubtype.getName());
        reclamation.setServiceName(serviceSubtype.getServiceName());

        return reclamation;
    }

    public List<ReclamationDomain> findAllByCompany(final Map<Long, List<Long>> companyFilterDomains) {
        log.info("[ReclamationUseCase] :: findAllByCompany :: Finding all reclamations by company");
        return gateway.findAllByCompany(companyFilterDomains)
                .stream()
                .map(this::fillServiceProperties)
                .toList();
    }

    public DashboardDomain buildDashboardByCompany(final Boolean isAdmin, final Map<Long, List<Long>> companyFilterDomains) {
        log.info("[ReclamationUseCase] :: buildDashboardByCompany :: Building dashboard by company");
        final var reclamations = getReclamations(isAdmin, companyFilterDomains);

        final var totalCount = (long) reclamations.size();
        final var answeredCount = reclamations.stream().filter(item -> !List.of(OPEN, IN_ANALYSIS).contains(item.getStatus())).count();
        final var unansweredCount = totalCount - answeredCount;
        final var resolvedCount = reclamations.stream().filter(item -> List.of(REJECTED, UNIDENTIFIED, RESOLVED).contains(item.getStatus())).count();
        final var unresolvedCount = totalCount - resolvedCount;

        return new DashboardDomain(totalCount, answeredCount, unansweredCount, resolvedCount, unresolvedCount);
    }

    private List<ReclamationDomain> getReclamations(final Boolean isAdmin, final Map<Long, List<Long>> companyFilterDomains) {
        if(Boolean.TRUE.equals(isAdmin)) {
            return gateway.findAll();
        }
        return gateway.findAllByCompany(companyFilterDomains);
    }

    public ReportsDomain buildReportsByCompany(final Map<Long, List<Long>> companyFilterDomains) {
        log.info("[ReclamationUseCase] :: buildReportsByCompany :: Building report by company");
        final var reclamations = getReclamations(false, companyFilterDomains);
        final var heatmapData = reclamations.stream()
                .map(item -> new HeatmapDataDomain(item.getLocalization().getLatitude(), item.getLocalization().getLongitude()))
                .toList();
        return new ReportsDomain(heatmapData);
    }
}
