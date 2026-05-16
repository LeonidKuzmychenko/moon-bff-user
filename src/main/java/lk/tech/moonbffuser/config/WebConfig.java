package lk.tech.moonbffuser.config;

import lk.tech.moonbffuser.web.AuthClient;
import lk.tech.moonbffuser.web.DbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

    @Bean
    public AuthClient authServiceClient(@Value("${app.auth-service.url}") String url) {
        return buildClient(url, AuthClient.class);
    }

    @Bean
    public DbClient dbServiceClient(@Value("${app.db-service.url}") String url) {
        return buildClient(url, DbClient.class);
    }

    private <T> T buildClient(String baseUrl, Class<T> type) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(
                        RestClientAdapter.create(restClient)
                ).build();

        return factory.createClient(type);
    }
}
