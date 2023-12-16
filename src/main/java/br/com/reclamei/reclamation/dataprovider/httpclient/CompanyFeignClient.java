package br.com.reclamei.reclamation.dataprovider.httpclient;

import br.com.reclamei.reclamation.dataprovider.httpclient.config.FeignTokenInterceptor;
import br.com.reclamei.reclamation.dataprovider.httpclient.response.ServiceTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
    name = "company-client",
    configuration = FeignTokenInterceptor.class,
    dismiss404 = true
)
public interface CompanyFeignClient {
    @GetMapping("/service-types")
    List<ServiceTypeResponse> getServiceTypes();
}
