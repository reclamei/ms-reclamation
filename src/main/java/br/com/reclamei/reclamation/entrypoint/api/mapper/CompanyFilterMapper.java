package br.com.reclamei.reclamation.entrypoint.api.mapper;

import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationsCompanyBody;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyFilterMapper {

    default Map<Long, List<Long>> toDomain(final List<ReclamationsCompanyBody> request) {
        return request.stream()
            .collect(Collectors.toMap(
                ReclamationsCompanyBody::getServiceSubtypeId,
                ReclamationsCompanyBody::getLocations,
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }
            ));
    }

}
