package br.com.reclamei.reclamation.config.auth;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private String dynamicToken;

    public void setDynamicToken(String token) {
        this.dynamicToken = token;
    }

    public String getDynamicToken() {
        return dynamicToken;
    }
}
