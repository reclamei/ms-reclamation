package br.com.reclamei.reclamation.core.domain;

import br.com.reclamei.reclamation.core.enumerator.ReclamationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReclamationDomain {

    private Long id;

    private String description;

    private LocalDateTime analyzedAt;

    private String photo;

    private ReclamationStatus status = ReclamationStatus.OPEN;

    private LocalizationDomain localization = new LocalizationDomain();

}
