package br.com.reclamei.reclamation.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EvaluationDomain {

    private Long id;

    private String rating;

    private String comment;

    private LocalDateTime createdAt;

}
