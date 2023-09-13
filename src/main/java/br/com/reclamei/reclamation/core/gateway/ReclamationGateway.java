package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;

import java.util.List;

public interface ReclamationGateway {

    void save(ReclamationDomain domain);

    void updateStatus(Long id, ReclamationStatusType status);

    List<ReclamationDomain> findByCompany(Long serviceSubtypeId, Long locationId);

    List<ReclamationDomain> findByCitizen(Long citizenId);

    void deleteById(Long id);

}
