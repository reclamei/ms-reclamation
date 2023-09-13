package br.com.reclamei.reclamation.entrypoint.api.mapper;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResponseApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "reclamationId", target = "reclamation.id")
    ResponseDomain toDomain(ResponseCreateRequest request);

    @Mapping(source = "reclamation.id", target = "reclamationId")
    ResponseResponse toResponse(ResponseDomain domain);

    @Mapping(source = "reclamationId", target = "reclamation.id")
    ResponseDomain toDomain(ResponseUpdateRequest request);

    List<ResponseResponse> toResponse(List<ResponseDomain> domain);

}
