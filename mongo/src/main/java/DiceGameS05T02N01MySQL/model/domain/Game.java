package DiceGameS05T02N01MySQL.model.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Document(collection = "game")
//@Entity
public class Game {
    @Id
    private String gameId;
    private int dice1;
    private int dice2;
    private Integer playerId;
    private boolean win;

}
