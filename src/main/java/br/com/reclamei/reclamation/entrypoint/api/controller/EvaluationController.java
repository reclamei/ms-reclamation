package br.com.reclamei.reclamation.entrypoint.api.controller;

import br.com.reclamei.reclamation.entrypoint.api.dto.EvaluationCreateRequest;
import br.com.reclamei.reclamation.entrypoint.api.facade.EvaluationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evaluations")
public class EvaluationController implements EvaluationsApi {

    private final EvaluationFacade facade;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody final EvaluationCreateRequest body) {
        facade.create(body);
        return ResponseEntity.status(CREATED).build();
    }

}
