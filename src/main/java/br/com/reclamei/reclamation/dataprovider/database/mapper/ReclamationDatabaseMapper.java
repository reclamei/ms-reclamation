package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.ReclamationEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = LocalizationDatabaseMapper.class)
public interface ReclamationDatabaseMapper {

    ReclamationDomain toDomain(ReclamationEntity entity);

    ReclamationEntity toEntity(ReclamationDomain domain);

    List<ReclamationDomain> toDomain(List<ReclamationEntity> entity);

    @AfterMapping
    default void atualizarObjeto(@MappingTarget ReclamationEntity entity) {
        entity.getLocalization().setReclamation(entity);
    }
}
