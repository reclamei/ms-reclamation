package br.com.reclamei.reclamation.dataprovider.httpclient.mapper;

import br.com.reclamei.reclamation.core.domain.ServiceTypeDomain;
import br.com.reclamei.reclamation.dataprovider.httpclient.response.ServiceTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceTypeMapper {

    ServiceTypeDomain toDomain(ServiceTypeResponse response);

    List<ServiceTypeDomain> toDomain(List<ServiceTypeResponse> response);

}
