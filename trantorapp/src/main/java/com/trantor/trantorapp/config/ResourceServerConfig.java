package com.trantor.trantorapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * ResourceServerConfig
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${application.credentials.resource.id}")
    private String resourceId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/v2/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
    }

    @Bean
    @Primary
    public RemoteTokenServices tokenServices(@Value("${application.credentials.auth.server}") String authServer,
                                             @Value("${application.credentials.client.id}") String clientId,
                                             @Value("${application.credentials.client.secret}") String clientSecret) {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(authServer);
        services.setClientId(clientId);
        services.setClientSecret(clientSecret);
        return services;
    }
}
