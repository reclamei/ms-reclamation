package br.com.reclamei.reclamation.entrypoint.api.facade;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.core.usecase.ReclamationUseCase;
import br.com.reclamei.reclamation.entrypoint.api.dto.DashboardResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationUpdateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationsCompanyBody;
import br.com.reclamei.reclamation.entrypoint.api.mapper.CompanyFilterMapper;
import br.com.reclamei.reclamation.entrypoint.api.mapper.ReclamationApiMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record ReclamationFacade(ReclamationApiMapper mapper, CompanyFilterMapper companyMapper, ReclamationUseCase useCase) {

    public void create(ReclamationCreateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public void update(final ReclamationUpdateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public void updateStatus(Long id, String status) {
        useCase.updateStatus(id, ReclamationStatusType.getByName(status));
    }

    public List<ReclamationResponse> findByCompany(Long serviceSubtypeId, Long locationId) {
        return mapper.toResponse(useCase.findByCompany(serviceSubtypeId, locationId));
    }

    public List<ReclamationResponse> findByCitizen(Long citizenId) {
        return mapper.toResponse(useCase.findByCitizen(citizenId));
    }

    public void deleteById(Long id) {
        useCase.deleteById(id);
    }

    public List<ReclamationResponse> findAllByCompany(List<ReclamationsCompanyBody> request) {
        var domain = companyMapper.toDomain(request);
        return mapper.toResponse(useCase.findAllByCompany(domain));
    }

    public DashboardResponse buildDashboardByCompany(List<ReclamationsCompanyBody> request) {
        var domain = companyMapper.toDomain(request);
        return mapper.toResponse(useCase.buildDashboardByCompany(domain));
    }
}
