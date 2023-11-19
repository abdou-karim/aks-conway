package sn.aks.gameoflife.web.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameCreateRequestDto {
  private String id;
  private String label;
  private int rows;
  private int columns;
  private boolean[][] grid;
  private boolean[][] nextGeneration;

}
