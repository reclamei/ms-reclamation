package br.com.reclamei.reclamation.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDomain {

    private Long id;

    private String description;

    private LocalDateTime createdAt;

    private ReclamationDomain reclamation;

    private EvaluationDomain evaluation = new EvaluationDomain();

}
