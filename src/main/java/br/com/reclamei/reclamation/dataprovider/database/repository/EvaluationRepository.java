package br.com.reclamei.reclamation.dataprovider.database.repository;

import br.com.reclamei.reclamation.dataprovider.database.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {

}
