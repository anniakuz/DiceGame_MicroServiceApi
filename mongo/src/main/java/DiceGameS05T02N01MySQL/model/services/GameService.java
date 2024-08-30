package DiceGameS05T02N01MySQL.model.services;


import DiceGameS05T02N01MySQL.model.domain.Game;
import DiceGameS05T02N01MySQL.model.dto.CheckPlayerDTO;
import DiceGameS05T02N01MySQL.model.dto.Player;
import DiceGameS05T02N01MySQL.model.dto.PlayerDTO;

import java.util.List;
import java.util.Map;

public interface GameService {
    public Game playGame(CheckPlayerDTO playerDTO);

    List<Game> getAllGamesWithPlayer(CheckPlayerDTO playerDTO);
    /*List<Game> getAllGames();

    public Map<String, Double> getAllPlayersRates();

    void deleteAllGames();

    Double getRateForAll();

    List<Player> getPlayerWithTheLowestRate();

    List<Player> getPlayerWithTheHighestRate();*/
/*
    public void deleteAllGames(Integer playerId);

      */
}
