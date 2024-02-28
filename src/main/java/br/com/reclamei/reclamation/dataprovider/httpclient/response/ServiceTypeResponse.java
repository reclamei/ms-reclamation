package br.com.reclamei.reclamation.dataprovider.httpclient.response;

import br.com.reclamei.reclamation.core.domain.ServiceSubtypeDomain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ServiceTypeResponse {

    private Long id;
    private String name;
    private String description;
    private List<ServiceSubtypeResponse> subtypes = new ArrayList<>();

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ServiceSubtypeResponse {
        private Long id;
        private String name;
        private String description;
    }

}
