package br.com.reclamei.reclamation.entrypoint.api.mapper;

import br.com.reclamei.reclamation.core.domain.EvaluationDomain;
import br.com.reclamei.reclamation.entrypoint.api.dto.EvaluationCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationApiMapper {

    EvaluationDomain toDomain(EvaluationCreateRequest request);

}
