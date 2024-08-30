package DiceGameS05T02N01MySQL.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlayerDTO {

    private Integer playerId;

    private String name;

    private LocalDate registerDate;
    //@Transient
    //Double rate;
    private String password;

  //  @JsonIgnore
   // @OneToMany(mappedBy = "player")
    //@Transient
   // private List<Game> games;

    public PlayerDTO(String name, String password) {
        this.name = name;
        this.password = password;
        this.registerDate = LocalDate.now();
    }



}
