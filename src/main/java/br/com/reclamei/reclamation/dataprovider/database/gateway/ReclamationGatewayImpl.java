package br.com.reclamei.reclamation.dataprovider.database.gateway;

import br.com.reclamei.reclamation.core.domain.ReclamationDomain;
import br.com.reclamei.reclamation.core.exception.NotFoundException;
import br.com.reclamei.reclamation.core.gateway.ReclamationGateway;
import br.com.reclamei.reclamation.core.type.ReclamationStatusType;
import br.com.reclamei.reclamation.dataprovider.database.mapper.ReclamationDatabaseMapper;
import br.com.reclamei.reclamation.dataprovider.database.repository.ReclamationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ReclamationGatewayImpl implements ReclamationGateway {

    private final ReclamationDatabaseMapper mapper;
    private final ReclamationRepository repository;

    @Override
    public void save(final ReclamationDomain domain) {
        var entity = mapper.toEntity(domain);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void updateStatus(final Long id, final ReclamationStatusType status) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("[ReclamationGatewayImpl] :: updateStatus :: Reclamation with id %s not found", id));
        }
        repository.updateStatus(id, status);
    }

    @Override
    public List<ReclamationDomain> findByCompany(final Long serviceSubtypeId, final Long locationId) {
        var entities = repository.findByServiceSubtypeIdAndLocalizationLocationId(serviceSubtypeId, locationId);
        return mapper.toDomain(entities);
    }

    @Override
    public List<ReclamationDomain> findByCitizen(final Long citizenId) {
        var entities = repository.findByCitizenId(citizenId);
        return mapper.toDomain(entities);
    }

    @Override
    public void deleteById(final Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(format("[ReclamationGatewayImpl] :: deleteById :: Reclamation with id %s not found", id));
        }
        repository.deleteById(id);
    }

    @Override
    public List<ReclamationDomain> findAllByCompany(final Map<Long, List<Long>> companyFilter) {
        final var entities = repository.findByServiceSubtypeIdIn(companyFilter.keySet());
        final var entitiesFiltered = entities.stream()
            .filter(entity -> companyFilter.get(entity.getServiceSubtypeId()).contains(entity.getLocalization().getLocationId()))
            .toList();

        return mapper.toDomain(entitiesFiltered);
    }

    @Override
    public List<ReclamationDomain> findAll() {
        return mapper.toDomain(repository.findAll());
    }

}
