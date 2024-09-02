package br.com.reclamei.reclamation.dataprovider.database.repository;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.dataprovider.database.entity.ReclamationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReclamationRepository extends JpaRepository<ReclamationEntity, Long> {

    @Modifying
    @Query("UPDATE ReclamationEntity SET status = :status WHERE id = :id")
    void updateStatus(Long id, ReclamationStatusType status);

    List<ReclamationEntity> findByCitizenId(Long citizenId);

    List<ReclamationEntity> findByServiceSubtypeIdAndLocalizationLocationId(Long serviceTypeId, Long locationId);

    List<ReclamationEntity> findByServiceSubtypeIdIn(Set<Long> serviceTypeIds);
}
