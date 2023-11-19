package sn.aks.gameoflife.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sn.aks.gameoflife.data.model.GameModel;

@Repository
public interface GameRepository extends MongoRepository<GameModel, String> {
  GameModel findByLabel(String label);
}
