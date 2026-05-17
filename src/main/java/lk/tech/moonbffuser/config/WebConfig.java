package lk.tech.moonbffuser.config;

import lk.tech.moonbffuser.web.AuthClient;
import lk.tech.moonbffuser.web.DbClient;
import lk.tech.moonbffuser.web.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class WebConfig {

    @Bean
    public MinioClient minioClient(@Value("${app.minio-service.url}") String url) {
        return buildClient(url, MinioClient.class, false);
    }

    @Bean
    public AuthClient authServiceClient(@Value("${app.auth-service.url}") String url) {
        return buildClient(url, AuthClient.class, true);
    }

    @Bean
    public DbClient dbServiceClient(@Value("${app.db-service.url}") String url) {
        return buildClient(url, DbClient.class, true);
    }

    private <T> T buildClient(String baseUrl, Class<T> type, boolean logBody) {
        RestClient.Builder builder = RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(loggingInterceptor(logBody));

        if (logBody) {
            builder.requestFactory(new BufferingClientHttpRequestFactory(
                    new SimpleClientHttpRequestFactory()
            ));
        }

        RestClient restClient = builder.build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(
                        RestClientAdapter.create(restClient)
                ).build();

        return factory.createClient(type);
    }

    private ClientHttpRequestInterceptor loggingInterceptor(boolean logBody) {
        return (request, body, execution) -> {
            log.info("Outgoing request: {} {}", request.getMethod(), request.getURI());

            if (logBody && body.length > 0) {
                log.info("Outgoing request body: {}", new String(body, StandardCharsets.UTF_8));
            }

            var response = execution.execute(request, body);

            log.info("Incoming response: {} {}", response.getStatusCode(), response.getStatusText());

            if (logBody) {
                byte[] responseBody = StreamUtils.copyToByteArray(response.getBody());

                if (responseBody.length > 0) {
                    log.info("Incoming response body: {}", new String(responseBody, StandardCharsets.UTF_8));
                }
            }

            return response;
        };
    }
}