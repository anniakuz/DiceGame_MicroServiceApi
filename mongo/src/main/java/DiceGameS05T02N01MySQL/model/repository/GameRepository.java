package DiceGameS05T02N01MySQL.model.repository;


import DiceGameS05T02N01MySQL.model.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    public List<Game> getGamesByPlayerId(Integer playerId);
}
