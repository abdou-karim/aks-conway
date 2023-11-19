package sn.aks.gameoflife.data.model.subclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CellModel {
  private int row;
  private int col;
  private boolean alive;
}
