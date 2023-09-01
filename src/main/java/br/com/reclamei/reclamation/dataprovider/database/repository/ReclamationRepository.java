package br.com.reclamei.reclamation.dataprovider.database.repository;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.enumerator.ReclamationStatus;
import br.com.reclamei.reclamation.dataprovider.database.entity.ReclamationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<ReclamationEntity, Long> {

    @Modifying
    @Query("UPDATE ReclamationEntity SET status = :status WHERE id = :id")
    void updateStatus(Long id, ReclamationStatus status);

    List<ReclamationDomain> findByCitizenId(Long citizenId);

    List<ReclamationDomain> findByServiceSubtypeIdAndLocalizationLocationId(Long serviceTypeId, Long locationId);

}
