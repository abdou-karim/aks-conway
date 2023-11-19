package sn.aks.gameoflife.service;

import org.springframework.http.ResponseEntity;
import sn.aks.gameoflife.data.model.GameModel;
import sn.aks.gameoflife.data.model.subclass.CellModel;
import sn.aks.gameoflife.web.dto.Response.GameResponseDto;

import java.util.List;

public interface GameService {
  ResponseEntity<GameResponseDto> initializeGrid(int gridSize);

  ResponseEntity<GameResponseDto> updateGeneration(String gameName);

  ResponseEntity<GameResponseDto> updateGrid(CellModel cellModel);

  void displayGrid();

  ResponseEntity<GameResponseDto> saveGame(GameModel gameModel);

  ResponseEntity<List<GameResponseDto>> loadGame();

  ResponseEntity<GameResponseDto> loadGameByName(String gameName);
}
