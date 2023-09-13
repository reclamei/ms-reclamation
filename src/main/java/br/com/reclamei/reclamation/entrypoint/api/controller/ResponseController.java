package br.com.reclamei.reclamation.entrypoint.api.controller;

import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseResponse;
import br.com.reclamei.reclamation.entrypoint.api.dto.ResponseUpdateRequest;
import br.com.reclamei.reclamation.entrypoint.api.facade.ResponseFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/responses")
public class ResponseController implements ResponsesApi {

    private final ResponseFacade facade;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody final ResponseCreateRequest body) {
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
    @GetMapping("/reclamation/{reclamationId}")
    public ResponseEntity<List<ResponseResponse>> findByReclamation(@PathVariable final Long reclamationId) {
        return ResponseEntity.status(OK).body(facade.findByReclamation(reclamationId));
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody final ResponseUpdateRequest body) {
        facade.update(body);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
