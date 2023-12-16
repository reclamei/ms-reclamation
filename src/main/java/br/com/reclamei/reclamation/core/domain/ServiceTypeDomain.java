package br.com.reclamei.reclamation.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ServiceTypeDomain {

    private Long id;
    private String name;
    private String description;
    private List<ServiceSubtypeDomain> subtypes = new ArrayList<>();

}
