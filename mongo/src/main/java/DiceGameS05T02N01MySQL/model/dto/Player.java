package DiceGameS05T02N01MySQL.model.dto;


import DiceGameS05T02N01MySQL.model.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Player {

    private Integer playerId;

    private String name;

    private LocalDate registerDate;

    Double rate;
    private String password;
    private List<Game> games;



    public Player(String name) {
        this.name = name;
        this.registerDate = LocalDate.now();
    }

   public Double calculateRate(){
        Double rate = 0.0;
        if(games!=null&&!games.isEmpty()) {
            int winGames = games.stream().filter(game -> game.isWin()).toList().size();
            rate = (double) ((winGames * 100) / games.size());
            this.rate = rate;
            return rate;
        }
        else {
            this.rate = rate;
            return   rate;
        }
    }

}
