package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.ServiceSubtypeDomain;

public interface CompanyGateway {
    ServiceSubtypeDomain getServiceSubtype(final Long id);

}
