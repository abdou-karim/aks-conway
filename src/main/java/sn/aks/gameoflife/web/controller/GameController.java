package sn.aks.gameoflife.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sn.aks.gameoflife.data.model.GameModel;
import sn.aks.gameoflife.data.model.subclass.CellModel;
import sn.aks.gameoflife.service.GameService;
import sn.aks.gameoflife.web.api.GameApi;
import sn.aks.gameoflife.web.dto.Response.GameResponseDto;

import java.util.List;

@RestController
public class GameController implements GameApi {
  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public ResponseEntity<GameResponseDto> initialize(int gridSize) {
    return gameService.initializeGrid(gridSize);
  }


  @Override
  public ResponseEntity<GameResponseDto> updateGrid(CellModel cellModel) {
    return gameService.updateGrid(cellModel);
  }

  @Override
  public ResponseEntity<GameResponseDto> updateGeneration(String gameName) {
    return gameService.updateGeneration(gameName);
  }

  @Override
  public ResponseEntity<GameResponseDto> saveGame(GameModel gameModel) {
    return gameService.saveGame(gameModel);
  }

  @Override
  public ResponseEntity<List<GameResponseDto>> loadGame() {
    return gameService.loadGame();

  }

  @Override
  public ResponseEntity<GameResponseDto> loadGameByName(String gameName) {
    return gameService.loadGameByName(gameName);
  }


}
