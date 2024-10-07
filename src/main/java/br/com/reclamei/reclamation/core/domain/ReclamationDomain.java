package br.com.reclamei.reclamation.core.domain;

import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReclamationDomain {

    private Long id;

    private String description;

    private Long citizenId;

    private Long serviceSubtypeId;

    private String serviceName;

    private String serviceSubtypeName;

    private LocalDateTime createdAt;

    private LocalDateTime analyzedAt;

    private String photo;

    private ReclamationStatusType status = ReclamationStatusType.OPEN;

    private LocalizationDomain localization = new LocalizationDomain();

    private ResponseDomain response = new ResponseDomain();

    private List<ResponseDomain> responses = new ArrayList<>();

}
