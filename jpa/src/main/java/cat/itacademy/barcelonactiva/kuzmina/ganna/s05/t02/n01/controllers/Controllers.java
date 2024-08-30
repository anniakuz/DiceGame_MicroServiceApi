package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.exceptions.HttpException;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.PlayerDTO;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/players")
public class Controllers {

    private final PlayerService playerService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPlayers(){

        return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllPlayers());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK).body(playerService.findPlayerById(id));
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getPlayerByIdWithParam(@RequestParam Integer id){

        return ResponseEntity.status(HttpStatus.OK).body(playerService.findPlayerById(id));
    }

    @GetMapping("/getName")
    public ResponseEntity<?> getPlayerByIdWithParam(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        String token = authHeader.substring(7);
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerByName(token));
    }


    @PostMapping("/player/signIn")
    public ResponseEntity<?> addPlayer(@RequestBody Player player){
        Player savedPlayer;
        try {
            savedPlayer = playerService.createPlayer(player);
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    @PostMapping("/addWithParams")
    public ResponseEntity<?> addPlayerWithParams(@RequestParam String name,@RequestParam String password){
        Player savedPlayer;
        try {
            savedPlayer = playerService.createPlayer(Player.builder().name(name).password(password).build());
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    @PutMapping("/editName")
    public ResponseEntity<?> updatePlayer(@RequestBody Player player,@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        String token = authHeader.substring(7);
        try{
            Player updatedPlayer = playerService.editPlayer(token,player);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlayer);
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }

    }

    @PostMapping("/player/login")
    public ResponseEntity<?> playGame(@RequestBody Player player) {
        try{
            String token = playerService.login(player);
        }
        catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.login(player));

    }

@GetMapping("/player/checkToken/{authHeader}")
public ResponseEntity<?> checkToken(@PathVariable String authHeader){
    /*try{
        String token = playerService.login(player);
    }
    catch(HttpException ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(playerService.login(player));*/


    if(authHeader==null){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authorized");

    }
    String token = authHeader.substring(7);
    PlayerDTO playerDTO = playerService.checkPlayer(token);
    return ResponseEntity.status(HttpStatus.OK).body(playerDTO);

}

}
