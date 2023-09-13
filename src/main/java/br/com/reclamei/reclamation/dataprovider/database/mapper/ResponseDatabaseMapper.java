package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.ResponseEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {EvaluationDatabaseMapper.class, ResponseDatabaseMapper.class})
public interface ResponseDatabaseMapper {

    ResponseDomain toDomain(ResponseEntity entity);

    @Mapping(source = "id", target = "evaluation.id")
    ResponseEntity toEntity(ResponseDomain domain);

    List<ResponseDomain> toDomain(List<ResponseEntity> entity);

    @AfterMapping
    default void atualizarObjeto(@MappingTarget ResponseEntity entity) {
        entity.getEvaluation().setResponse(entity);
    }

}
