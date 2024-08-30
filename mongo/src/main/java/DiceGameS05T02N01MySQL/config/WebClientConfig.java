package DiceGameS05T02N01MySQL.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class WebClientConfig {
    @Value( "${webclient.base-url}" )
    private String BASE_URL;
    @Bean
    public WebClient webClient(){
        return WebClient.create(BASE_URL);
    }

}
