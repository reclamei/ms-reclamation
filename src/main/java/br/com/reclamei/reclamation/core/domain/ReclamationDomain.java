package br.com.reclamei.reclamation.core.domain;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReclamationDomain {

    private Long id;

    private String description;

    private Long citizenId;

    private Long serviceSubtypeId;

    private LocalDateTime analyzedAt;

    private String photo;

    private ReclamationStatusType status = ReclamationStatusType.OPEN;

    private LocalizationDomain localization = new LocalizationDomain();

}
