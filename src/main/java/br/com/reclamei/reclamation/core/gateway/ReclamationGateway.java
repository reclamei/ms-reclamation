package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.enumerator.ReclamationStatusEnum;

import java.util.List;

public interface ReclamationGateway {

    void save(ReclamationDomain domain);

    void updateStatus(Long id, ReclamationStatusEnum status);

    List<ReclamationDomain> findByCompany(Long serviceSubtypeId, Long locationId);

    List<ReclamationDomain> findByCitizen(Long citizenId);

    void deleteById(Long id);

}
