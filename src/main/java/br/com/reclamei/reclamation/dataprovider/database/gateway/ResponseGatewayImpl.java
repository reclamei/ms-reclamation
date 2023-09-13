package br.com.reclamei.reclamation.dataprovider.database.gateway;

import br.com.reclamei.reclamation.core.domain.ResponseDomain;
import br.com.reclamei.reclamation.core.exception.NotFoundException;
import br.com.reclamei.reclamation.core.gateway.ResponseGateway;
import br.com.reclamei.reclamation.dataprovider.database.mapper.ResponseDatabaseMapper;
import br.com.reclamei.reclamation.dataprovider.database.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public record ResponseGatewayImpl(ResponseDatabaseMapper mapper, ResponseRepository repository) implements ResponseGateway {

    @Override
    public void save(final ResponseDomain domain) {
        var entity = mapper.toEntity(domain);
        repository.save(entity);
    }

    @Override
    public List<ResponseDomain> findByReclamation(final Long reclamationId) {
        var entities = repository.findByReclamationId(reclamationId);
        return mapper.toDomain(entities);
    }

    @Override
    public void deleteById(final Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("[ResponseGatewayImpl] :: deleteById :: Response with id %s not found", id));
        }
        repository.deleteById(id);
    }

}
