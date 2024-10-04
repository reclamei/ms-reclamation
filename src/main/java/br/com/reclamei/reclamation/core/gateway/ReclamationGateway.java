package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;

import java.util.List;
import java.util.Map;

public interface ReclamationGateway {

    void save(ReclamationDomain domain);

    void updateStatus(Long id, ReclamationStatusType status);

    List<ReclamationDomain> findByCitizen(Long citizenId);

    void deleteById(Long id);

    List<ReclamationDomain> findAllByCompany(Map<Long, List<Long>> companyFilterDomains);

    List<ReclamationDomain> findAll();

    ReclamationDomain getById(Long id);
}
