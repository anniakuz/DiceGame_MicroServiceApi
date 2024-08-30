package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain;


import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Game {

    private String gameId;
    private int dice1;
    private int dice2;
    private Integer playerId;
    private boolean win;


}
