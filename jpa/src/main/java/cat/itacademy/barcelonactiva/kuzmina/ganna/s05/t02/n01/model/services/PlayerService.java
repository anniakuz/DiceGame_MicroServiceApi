package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.services;




import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.PlayerDTO;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain.Player;

import java.util.List;

public interface PlayerService {
    public PlayerDTO checkPlayer(String token);
    public Player findPlayerById(Integer playerId);

    public Player createPlayer(Player player);
    public List<Player> getAllPlayers();

    public Player editPlayer(String token, Player player);
    public String login(Player player);
    public Player getPlayerByName(String token);


}
