package br.com.reclamei.reclamation.entrypoint.api.facade;

import br.com.reclamei.reclamation.core.usecase.ResponseUseCase;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseUpdateRequest;
import br.com.reclamei.reclamation.entrypoint.api.mapper.ResponseApiMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record ResponseFacade(ResponseApiMapper mapper, ResponseUseCase useCase) {

    public void create(ResponseCreateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public void update(final ResponseUpdateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

    public List<ResponseResponse> findByReclamation(Long reclamationId) {
        return mapper.toResponse(useCase.findByReclamation(reclamationId));
    }

    public void deleteById(Long id) {
        useCase.deleteById(id);
    }

}
