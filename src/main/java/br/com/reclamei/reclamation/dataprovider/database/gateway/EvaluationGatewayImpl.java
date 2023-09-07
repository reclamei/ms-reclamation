package br.com.reclamei.reclamation.dataprovider.database.gateway;

import br.com.reclamei.reclamation.core.domain.EvaluationDomain;
import br.com.reclamei.reclamation.core.gateway.EvaluationGateway;
import br.com.reclamei.reclamation.dataprovider.database.mapper.EvaluationDatabaseMapper;
import br.com.reclamei.reclamation.dataprovider.database.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public record EvaluationGatewayImpl(EvaluationDatabaseMapper mapper, EvaluationRepository repository) implements EvaluationGateway {

    @Override
    public void save(final EvaluationDomain domain) {
        var entity = mapper.toEntity(domain);
        repository.save(entity);
    }

}
