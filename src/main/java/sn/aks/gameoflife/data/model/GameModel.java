package sn.aks.gameoflife.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameModel {
  @Id
  private String id;
  private String label;
  private int rows;
  private int columns;
  private boolean[][] grid;
  private boolean[][] nextGeneration;

  public GameModel(String label,int rows, int columns) {
    this.label = label;
    this.rows = rows;
    this.columns = columns;
    this.grid = new boolean[rows][columns];
    this.nextGeneration = new boolean[rows][columns];
  }
}
