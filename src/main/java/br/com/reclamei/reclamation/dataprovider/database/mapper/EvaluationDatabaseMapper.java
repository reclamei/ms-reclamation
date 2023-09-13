package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.EvaluationDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.EvaluationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvaluationDatabaseMapper {

    EvaluationDomain toDomain(EvaluationEntity entity);

    @Mapping(source = "responseId", target = "response.id")
    EvaluationEntity toEntity(EvaluationDomain domain);

}
