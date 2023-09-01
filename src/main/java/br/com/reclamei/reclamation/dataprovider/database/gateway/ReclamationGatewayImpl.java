package br.com.reclamei.reclamation.dataprovider.database.gateway;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.enumerator.ReclamationStatus;
import br.com.reclamei.reclamation.core.exception.NotFoundException;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import br.com.reclamei.reclamation.dataprovider.database.mapper.ReclamationDatabaseMapper;
import br.com.reclamei.reclamation.dataprovider.database.repository.ReclamationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public record ReclamationGatewayImpl(ReclamationDatabaseMapper mapper, ReclamationRepository repository) implements ReclamationGateway {

    @Override
    public void save(final ReclamationDomain domain) {
        var entity = mapper.toEntity(domain);
        repository.save(entity);
    }

    @Override
    public void updateStatus(final Long id, final ReclamationStatus status) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("[ReclamationGatewayImpl] :: updateStatus :: Reclamation with id %s not found", id));
        }
        repository.updateStatus(id, status);
    }

    @Override
    public List<ReclamationDomain> findByCompany(final Long serviceSubtypeId, final Long locationId) {
        return repository.findByServiceSubtypeIdAndLocalizationLocationId(serviceSubtypeId, locationId);
    }

    @Override
    public List<ReclamationDomain> findByCitizen(final Long citizenId) {
        return repository.findByCitizenId(citizenId);
    }

    @Override
    public void deleteById(final Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("[ReclamationGatewayImpl] :: deleteById :: Reclamation with id %s not found", id));
        }
        repository.deleteById(id);
    }

}
