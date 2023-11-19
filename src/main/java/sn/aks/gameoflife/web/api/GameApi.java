package sn.aks.gameoflife.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.aks.gameoflife.data.model.GameModel;
import sn.aks.gameoflife.data.model.subclass.CellModel;
import sn.aks.gameoflife.web.dto.Response.GameResponseDto;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/game")
public interface GameApi {

  @GetMapping("initializeGrid")
  ResponseEntity<GameResponseDto> initialize(@RequestParam(name = "gridSize", defaultValue = "50")
                                             int gridSize);

  @PutMapping("/update/grid")
  ResponseEntity<GameResponseDto> updateGrid(@RequestBody CellModel cellModel);

  @GetMapping("/updateGeneration/{gameName}")
  ResponseEntity<GameResponseDto> updateGeneration(@PathVariable String gameName);

  @PostMapping("/save")
  ResponseEntity<GameResponseDto> saveGame(@RequestBody GameModel gameModel);

  @GetMapping("/load")
  ResponseEntity<List<GameResponseDto>> loadGame();

  @GetMapping("/name/{gameName}")
  ResponseEntity<GameResponseDto> loadGameByName(@PathVariable String gameName);


}
