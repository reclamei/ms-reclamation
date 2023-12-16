package br.com.reclamei.reclamation.dataprovider.httpclient.config;

import br.com.reclamei.reclamation.config.auth.TokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    private final TokenService tokenService;

    public FeignTokenInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void apply(RequestTemplate template) {
        String dynamicToken = tokenService.getDynamicToken();

        if (dynamicToken != null) {
            template.header("Authorization", dynamicToken);
        }
    }
}
