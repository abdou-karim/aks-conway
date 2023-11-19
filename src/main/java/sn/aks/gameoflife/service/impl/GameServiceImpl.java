package sn.aks.gameoflife.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.aks.gameoflife.data.mapper.GameMapper;
import sn.aks.gameoflife.data.model.GameModel;
import sn.aks.gameoflife.data.model.subclass.CellModel;
import sn.aks.gameoflife.data.repository.GameRepository;
import sn.aks.gameoflife.service.GameService;
import sn.aks.gameoflife.web.dto.Response.GameResponseDto;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
  private final GameRepository repository;

  public GameServiceImpl(GameRepository repository) {
    this.repository = repository;
  }

  private static Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

  @Override
  public ResponseEntity<GameResponseDto> initializeGrid(int gridSize) {
    GameModel gameModel = repository.findByLabel("INITIALE");

    if (gameModel == null) {
      gameModel = new GameModel("INITIALE", gridSize, gridSize);
      repository.save(gameModel);
    } else {
      gameModel.setRows(gridSize);
      gameModel.setColumns(gridSize);
      gameModel.setGrid(new boolean[gridSize][gridSize]);
      gameModel.setNextGeneration(new boolean[gridSize][gridSize]);
      repository.save(gameModel);
    }

    return new ResponseEntity<>(GameMapper.INSTANCE.toDto(gameModel), HttpStatus.OK);
  }


  @Override
  public ResponseEntity<GameResponseDto> updateGeneration(String gameName) {
    GameModel gameModel = repository.findByLabel(gameName);
    int rows = gameModel.getRows();
    int columns = gameModel.getColumns();
    boolean[][] nextGeneration = new boolean[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int liveNeighbors = countLiveNeighbors(gameModel, i, j);

        if (gameModel.getGrid()[i][j]) {
          // Cell is alive
          nextGeneration[i][j] = liveNeighbors >= 2 && liveNeighbors <= 3;
        } else {
          // Cell is dead
          nextGeneration[i][j] = liveNeighbors == 3;
        }
      }
    }

    // Update the grid with the next generation
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        gameModel.getGrid()[i][j] = nextGeneration[i][j];
      }
    }

    repository.save(gameModel);
    return new ResponseEntity<>(GameMapper.INSTANCE.toDto(gameModel), HttpStatus.OK);
  }

  private int countLiveNeighbors(GameModel gameModel, int row, int col) {
    int count = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }

        int neighborRow = row + i;
        int neighborCol = col + j;

        if (neighborRow >= 0 && neighborRow < gameModel.getRows() && neighborCol >= 0 && neighborCol < gameModel.getColumns()) {
          if (gameModel.getGrid()[neighborRow][neighborCol]) {
            count++;
          }
        }
      }
    }
    return count;
  }


  @Override
  public ResponseEntity<GameResponseDto> updateGrid(CellModel cellModel) {
    GameModel gameModel = getGameModel();
    int row = cellModel.getRow();
    int col = cellModel.getCol();
    if (row >= 0 && row < gameModel.getRows() && col >= 0 && col < gameModel.getColumns()) {
      gameModel.getGrid()[row][col] = cellModel.isAlive();
      repository.save(gameModel);
      return new ResponseEntity<>(GameMapper.INSTANCE.toDto(gameModel), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }

  @Override
  public void displayGrid() {
    GameModel gameModel = getGameModel();
    for (int i = 0; i < gameModel.getRows(); i++) {
      for (int j = 0; j < gameModel.getColumns(); j++) {
        System.out.print(gameModel.getGrid()[i][j] ? "X " : "- ");
      }
      System.out.println();
    }
    System.out.println();
  }

  @Override
  public ResponseEntity<GameResponseDto> saveGame(GameModel gameModel) {
    repository.save(gameModel);
    return new ResponseEntity<>(GameMapper.INSTANCE.toDto(gameModel), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<GameResponseDto>> loadGame() {
    List<GameModel> gameModels = repository.findAll();
    return new ResponseEntity<>(GameMapper.INSTANCE.toDtos(gameModels), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GameResponseDto> loadGameByName(String gameName) {
    GameModel gameModel = repository.findByLabel(gameName);
    return new ResponseEntity<>(GameMapper.INSTANCE.toDto(gameModel), HttpStatus.OK);
  }

  GameModel getGameModel() {
    return repository.findByLabel("INITIALE");

  }


}
