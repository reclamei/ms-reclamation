package br.com.reclamei.reclamation.dataprovider.httpclient.gateway;

import br.com.reclamei.reclamation.core.domain.ServiceSubtypeDomain;
import br.com.reclamei.reclamation.core.gateway.CompanyGateway;
import br.com.reclamei.reclamation.dataprovider.httpclient.CompanyFeignClient;
import br.com.reclamei.reclamation.dataprovider.httpclient.mapper.ServiceTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyGatewayImpl implements CompanyGateway {

    private final CompanyFeignClient client;

    private final ServiceTypeMapper serviceTypeMapper;

    private Map<Long, ServiceSubtypeDomain> serviceSubtypeDomains = new HashMap<>();

    @Override
    public ServiceSubtypeDomain getServiceSubtype(final Long id) {
        if(this.serviceSubtypeDomains == null || this.serviceSubtypeDomains.isEmpty()) {
            this.refreshCache();
        }
        return Optional.of(serviceSubtypeDomains.get(id)).orElse(new ServiceSubtypeDomain());
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public synchronized void serviceTypesCacheEvictTtl() {
        this.serviceSubtypeDomains = new HashMap<>();
    }

    private void refreshCache() {
        var serviceTypes = serviceTypeMapper.toDomain(client.getServiceTypes());

        this.serviceSubtypeDomains = serviceTypes.stream()
            .flatMap(service -> service.getSubtypes().stream()
                .peek(subtype -> subtype.setServiceName(service.getName())))
            .collect(Collectors.toMap(ServiceSubtypeDomain::getId, item -> item));
    }
}

