package br.com.reclamei.reclamation.entrypoint.api.facade;

import br.com.reclamei.reclamation.core.usecase.EvaluationUseCase;
import br.com.reclamei.reclamation.entrypoint.api.dto.EvaluationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.mapper.EvaluationApiMapper;
import org.springframework.stereotype.Component;

@Component
public record EvaluationFacade(EvaluationApiMapper mapper, EvaluationUseCase useCase) {

    public void create(EvaluationCreateRequest request) {
        var domain = mapper.toDomain(request);
        useCase.save(domain);
    }

}
