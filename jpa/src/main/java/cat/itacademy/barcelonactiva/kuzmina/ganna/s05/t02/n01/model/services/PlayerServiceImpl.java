package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.services;


import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.exceptions.HttpException;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.PlayerDTO;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain.Role;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public PlayerDTO checkPlayer(String token) {
    String name = jwtService.extractUsername(token);
    Player userDetails = playerRepository.getPlayerByName(name);
    boolean isValid = jwtService.isTokenValid(token,userDetails);
    PlayerDTO playerDTO = PlayerDTO.builder().id(userDetails.getPlayerId()).name(name).isTokenValid(isValid).build();
        return playerDTO;
    }

    @Override
    public Player findPlayerById(Integer playerId) {
        if(playerRepository.findAll().isEmpty()){
            throw new HttpException(HttpStatus.INSUFFICIENT_STORAGE,"There is no one");
        }
        return playerRepository.findById(playerId).orElseThrow(()-> new HttpException(HttpStatus.NOT_FOUND,
                "Could not find any player with ID "+playerId));
    }

    @Override
    public Player createPlayer(Player player) {
        if(playerRepository.getPlayerByName(player.getName())!=null){
            throw new HttpException(HttpStatus.BAD_REQUEST,"Player with this name already exist");
        }
        Player playerForSave = new Player(player.getName());
        playerForSave.setPassword(passwordEncoder.encode(player.getPassword()));
        playerForSave.setRole(Role.USER);

        return playerRepository.save(playerForSave);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player editPlayer(String token, Player player) {
        String name = jwtService.extractUsername(token);
        if(playerRepository.getPlayerByName(player.getName())!=null){
            throw new HttpException(HttpStatus.BAD_REQUEST,"Player with this name already exists");
        }
        Player playerUpdated = playerRepository.getPlayerByName(name);
        playerUpdated.setName(player.getName());
        return playerRepository.save(playerUpdated);

    }

    @Override
    public String login(Player player) {
        Player player1 = playerRepository.getPlayerByName(player.getName());
        if(player1==null||!passwordEncoder.matches(player.getPassword(), player1.getPassword())){
            throw new HttpException(HttpStatus.UNAUTHORIZED,"Login or password is not valid");
        }
        return jwtService.generateToken((UserDetails) player1);
    }

    public Player getPlayerByName(String token){
        String name = jwtService.extractUsername(token);

        return playerRepository.getPlayerByName(name);
    }

}
