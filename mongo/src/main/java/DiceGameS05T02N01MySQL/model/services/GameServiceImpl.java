package DiceGameS05T02N01MySQL.model.services;

import DiceGameS05T02N01MySQL.model.domain.Game;
import DiceGameS05T02N01MySQL.model.dto.CheckPlayerDTO;
import DiceGameS05T02N01MySQL.model.dto.Player;
import DiceGameS05T02N01MySQL.model.dto.PlayerDTO;
import DiceGameS05T02N01MySQL.model.repository.GameRepository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private  final PlayGameServiceImpl playGameService;
    private final PlayerWebClientServiceImpl playerWebClientService;
    @Override
    public Game playGame(CheckPlayerDTO playerDTO){
       // PlayerDTO playerDTO = playerWebClientService.getPlayer();
       /* Player player = Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build();*/
        if(playerDTO.isTokenValid()) {
            Game gamePlayed = playGameService.play();
            gamePlayed.setPlayerId(playerDTO.getId());

            return gameRepository.save(gamePlayed);
        }
        return null;
    }

    @Override
    public List<Game> getAllGamesWithPlayer(CheckPlayerDTO playerDTO) {
        return null;
    }
/*
    @Override
    public List<Game> getAllGamesWithPlayer() {
        PlayerDTO playerDTO = playerWebClientService.getPlayer();
        Player player = Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build();

        return gameRepository.getGamesByPlayerId(player.getPlayerId());
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Map<String, Double> getAllPlayersRates(){
        List<PlayerDTO> allPlayersDTO = playerWebClientService.getAllPlayers();
        List<Player> allPlayers = allPlayersDTO.stream().map(playerDTO -> Player.builder().playerId(playerDTO.getPlayerId())
                        .registerDate(playerDTO.getRegisterDate())
                                .password(playerDTO.getPassword())
                                        .name(playerDTO.getName())
                .build()).toList();
        allPlayers.forEach(player->player.setGames(gameRepository.getGamesByPlayerId(player.getPlayerId())));
        allPlayers.forEach(Player::calculateRate);
        Map<String, Double> allPlayersRates = allPlayers.stream().
                collect(Collectors.toMap(Player::getName, Player::getRate));
        return allPlayersRates;

    }


    @Override
    public void deleteAllGames(){
        PlayerDTO playerDTO = playerWebClientService.getPlayer();
        Player player = Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build();
        List<Game> allGames = getAllGamesWithPlayer();

        for(Game game:allGames) {
            gameRepository.deleteById(game.getGameId());
        }

    }
    @Override
    public Double getRateForAll(){
        List<PlayerDTO> allPlayersDTO = playerWebClientService.getAllPlayers();
        List<Player> allPlayers = allPlayersDTO.stream().map(playerDTO -> Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build()).toList();
        allPlayers.forEach(player->player.setGames(gameRepository.getGamesByPlayerId(player.getPlayerId())));
        allPlayers.forEach(Player::calculateRate);
        Double rate = allPlayers.stream().mapToDouble(Player::getRate).sum()/allPlayers.size();;
        return rate;
    }

    @Override
    public List<Player> getPlayerWithTheLowestRate(){
        List<PlayerDTO> allPlayersDTO = playerWebClientService.getAllPlayers();
        List<Player> allPlayers = allPlayersDTO.stream().map(playerDTO -> Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build()).toList();
        allPlayers.forEach(player->player.setGames(gameRepository.getGamesByPlayerId(player.getPlayerId())));
        allPlayers.forEach(Player::calculateRate);

        List<Player> listPlayersMin = null;
        Comparator<Player> comparator = Comparator.comparing(Player::getRate);
        Player playerMin = allPlayers.stream().min(comparator).get();
        listPlayersMin = allPlayers.stream().
                filter(player -> Objects.equals(player.getRate(), playerMin.getRate())).collect(Collectors.toList());
        return listPlayersMin;
    }

    @Override
    public List<Player> getPlayerWithTheHighestRate(){
        List<PlayerDTO> allPlayersDTO = playerWebClientService.getAllPlayers();
        List<Player> allPlayers = allPlayersDTO.stream().map(playerDTO -> Player.builder().playerId(playerDTO.getPlayerId())
                .registerDate(playerDTO.getRegisterDate())
                .password(playerDTO.getPassword())
                .name(playerDTO.getName())
                .build()).toList();
        allPlayers.forEach(player->player.setGames(gameRepository.getGamesByPlayerId(player.getPlayerId())));
        allPlayers.forEach(Player::calculateRate);

        List<Player> listPlayersMax = null;
        Comparator<Player> comparator = Comparator.comparing(Player::getRate);
        Player playerMax = allPlayers.stream().max(comparator).get();
        listPlayersMax = allPlayers.stream().
                filter(player -> Objects.equals(player.getRate(), playerMax.getRate())).collect(Collectors.toList());
        return listPlayersMax;
    }
*/

}
