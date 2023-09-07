package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.ResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {EvaluationDatabaseMapper.class, ResponseDatabaseMapper.class})
public interface ResponseDatabaseMapper {

    ResponseDomain toDomain(ResponseEntity entity);

    ResponseEntity toEntity(ResponseDomain domain);

    List<ResponseDomain> toDomain(List<ResponseEntity> entity);

}
