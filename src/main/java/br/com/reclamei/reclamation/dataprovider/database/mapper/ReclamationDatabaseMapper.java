package br.com.reclamei.reclamation.dataprovider.database.mapper;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.dataprovider.database.entity.ReclamationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { LocalizationDatabaseMapper.class })
public interface ReclamationDatabaseMapper {

    ReclamationDomain toDomain(ReclamationEntity entity);

    ReclamationEntity toEntity(ReclamationDomain domain);

}
