package br.com.reclamei.reclamation.entrypoint.api.controller;

import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ReclamationUpdateRequest;
import br.com.reclamei.reclamation.entrypoint.api.facade.ReclamationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reclamations")
public class ReclamationController implements ReclamationsApi {

    private final ReclamationFacade facade;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody final ReclamationCreateRequest body) {
        facade.create(body);
        return ResponseEntity.status(CREATED).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        facade.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<ReclamationResponse>> findByCitizen(@PathVariable final Long citizenId) {
        return ResponseEntity.status(OK).body(facade.findByCitizen(citizenId));
    }

    @Override
    @GetMapping("/service-subtype/{serviceSubtypeId}/location/{locationId}")
    public ResponseEntity<List<ReclamationResponse>> findByCompany(@PathVariable final Long serviceSubtypeId, @PathVariable final Long locationId) {
        return ResponseEntity.status(OK).body(facade.findByCompany(serviceSubtypeId, locationId));
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody final ReclamationUpdateRequest body) {
        facade.update(body);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    @PatchMapping("/{id}/update-status")
    public ResponseEntity<Void> updateStatus(@PathVariable final Long id, final String status) {
        facade.updateStatus(id, status);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
