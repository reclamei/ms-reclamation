package br.com.reclamei.reclamation.entrypoint.api.facade;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.core.usecase.ReclamationUseCase;
import br.com.reclamei.reclamation.entrypoint.api.dto.CompanyDashboardBody;
import br.com.reclamei.reclamation.entrypoint.api.dto.DashboardResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationUpdateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationsCompanyBody;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationsReportsBody;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReportsResponse;
import br.com.reclamei.reclamation.entrypoint.api.mapper.CompanyFilterMapper;
import br.com.reclamei.reclamation.entrypoint.api.mapper.ReclamationApiMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record ReclamationFacade(ReclamationApiMapper mapper, CompanyFilterMapper companyMapper, ReclamationUseCase useCase) {

    public void create(final ReclamationCreateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public void update(final ReclamationUpdateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public void updateStatus(final Long id, final String status) {
        useCase.updateStatus(id, ReclamationStatusType.getByName(status));
    }

    public List<ReclamationResponse> findByCitizen(final Long citizenId) {
        return mapper.toResponse(useCase.findByCitizen(citizenId));
    }

    public void deleteById(final Long id) {
        useCase.deleteById(id);
    }

    public List<ReclamationResponse> findAllByCompany(final List<ReclamationsCompanyBody> request) {
        var domain = companyMapper.toDomain(request);
        return mapper.toResponse(useCase.findAllByCompany(domain));
    }

    public DashboardResponse buildDashboardByCompany(final CompanyDashboardBody request) {
        var domain = companyMapper.toDomain(request.getCoverages());
        return mapper.toResponse(useCase.buildDashboardByCompany(request.isIsAdmin(), domain));
    }

    public ReportsResponse buildReportsByCompany(final ReclamationsReportsBody request) {
        var domain = companyMapper.toDomain(request.getCoverages());
        return mapper.toResponse(useCase.buildReportsByCompany(domain));
    }

    public ReclamationResponse getById(final Long id) {
        return mapper.toResponse(useCase.getById(id));
    }
}
