package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.LocalizationDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.LocalizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalizationDatabaseMapper {

    LocalizationDomain toDomain(LocalizationEntity entity);

    LocalizationEntity toEntity(LocalizationDomain domain);

}
