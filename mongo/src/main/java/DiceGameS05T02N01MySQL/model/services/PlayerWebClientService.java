package DiceGameS05T02N01MySQL.model.services;

import DiceGameS05T02N01MySQL.model.dto.CheckPlayerDTO;
import DiceGameS05T02N01MySQL.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerWebClientService {
    public CheckPlayerDTO getPlayer(String token);
}
