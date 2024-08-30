package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDTO {

    Integer id;
    String name;
    boolean isTokenValid;
}
