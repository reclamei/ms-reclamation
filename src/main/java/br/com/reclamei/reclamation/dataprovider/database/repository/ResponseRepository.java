package br.com.reclamei.reclamation.dataprovider.database.repository;

import br.com.reclamei.reclamation.dataprovider.database.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {

    List<ResponseEntity> findByReclamationId(Long reclamationId);

}
