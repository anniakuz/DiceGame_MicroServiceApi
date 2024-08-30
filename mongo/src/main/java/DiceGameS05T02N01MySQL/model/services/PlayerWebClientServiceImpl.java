package DiceGameS05T02N01MySQL.model.services;

import DiceGameS05T02N01MySQL.model.dto.CheckPlayerDTO;
import DiceGameS05T02N01MySQL.model.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerWebClientServiceImpl implements PlayerWebClientService{
private  final WebClient webClient;
    public List<PlayerDTO> getAllPlayers(String token){
        return webClient.get()
                .uri("/players/getAll/"+token).
                header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PlayerDTO>>() {})
                .block();

    }


    public CheckPlayerDTO getPlayer(String token){
        return webClient.get()
                .uri("/players/player/checkToken/"+token).
                header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CheckPlayerDTO>() {})
                .block();

    }

}
