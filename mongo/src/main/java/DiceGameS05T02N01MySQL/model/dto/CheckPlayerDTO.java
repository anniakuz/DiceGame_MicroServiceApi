package DiceGameS05T02N01MySQL.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPlayerDTO {

    Integer id;
    String name;
    boolean isTokenValid;
}
