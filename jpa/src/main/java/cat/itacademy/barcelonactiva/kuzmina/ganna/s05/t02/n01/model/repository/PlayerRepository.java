package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.repository;


import cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Player getPlayerByName(String name);
    Optional<Player> findByName(String name);


}
