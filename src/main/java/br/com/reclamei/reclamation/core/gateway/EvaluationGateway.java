package br.com.reclamei.reclamation.core.gateway;

import br.com.reclamei.reclamation.core.domain.EvaluationDomain;

public interface EvaluationGateway {

    void save(EvaluationDomain domain);

}
