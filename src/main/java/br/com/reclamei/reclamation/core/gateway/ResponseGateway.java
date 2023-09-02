package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;

import java.util.List;

public interface ResponseGateway {

    void save(ResponseDomain domain);

    List<ResponseDomain> findByReclamation(Long reclamationId);

    void deleteById(Long id);

}
