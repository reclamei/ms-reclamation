package br.com.reclamei.reclamation.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LocalizationDomain implements Serializable {

    private Long id;

    private String street;

    private String district;

    private String city;

    private String latitude;

    private String longitude;

}
