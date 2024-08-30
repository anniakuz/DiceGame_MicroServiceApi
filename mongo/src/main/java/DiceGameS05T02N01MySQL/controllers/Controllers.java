package DiceGameS05T02N01MySQL.controllers;


import DiceGameS05T02N01MySQL.exceptions.HttpException;
import DiceGameS05T02N01MySQL.model.domain.Game;
import DiceGameS05T02N01MySQL.model.dto.CheckPlayerDTO;
import DiceGameS05T02N01MySQL.model.dto.PlayerDTO;
import DiceGameS05T02N01MySQL.model.services.GameService;

import DiceGameS05T02N01MySQL.model.services.PlayerWebClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class Controllers {
    private final GameService gameService;
    private final PlayerWebClientService playerWebClientService;

    @PostMapping("/playGame")
    public ResponseEntity<?> playGame(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        CheckPlayerDTO playerDTO = playerWebClientService.getPlayer(authHeader);
        Game game = gameService.playGame(playerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping("/showGames")
    public ResponseEntity<?> getAllGameOfPlayer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        List<Game> games;
        try {
            CheckPlayerDTO playerDTO = playerWebClientService.getPlayer(authHeader);
            games = gameService.getAllGamesWithPlayer(playerDTO);
        } catch (HttpException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }
    /*
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGames(){
        try {
            gameService.getAllGames();
        } catch (HttpException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());
    }
    @GetMapping("/rates")
    public ResponseEntity<?> getAllPlayersRates(){
        try{
            gameService.getAllPlayersRates();
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllPlayersRates());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAllGames(){
        try{
            gameService.deleteAllGames();
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());
    }


    @GetMapping("/ranking")
    public ResponseEntity<?> getRankingForAll(){
        try{
            gameService.getRateForAll();
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getRateForAll());
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<?> getLoser(){
        try {
            gameService.getPlayerWithTheLowestRate();
        } catch (HttpException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getPlayerWithTheLowestRate());
    }


    @GetMapping("/ranking/winner")
    public ResponseEntity<?> getWinner() {
        try {
            gameService.getPlayerWithTheHighestRate();

        } catch (HttpException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getPlayerWithTheHighestRate());
    }*/


}
