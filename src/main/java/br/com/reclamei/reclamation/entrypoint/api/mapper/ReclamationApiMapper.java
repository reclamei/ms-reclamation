package br.com.reclamei.reclamation.entrypoint.api.mapper;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationUpdateRequest;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReclamationApiMapper {

    @Mapping(target = "id", ignore = true)
    ReclamationDomain toDomain(ReclamationCreateRequest resquest);

    ReclamationResponse toResponse(ReclamationDomain domain);

    ReclamationDomain toDomain(ReclamationUpdateRequest resquest);

    List<ReclamationResponse> toResponse(List<ReclamationDomain> domain);

}
