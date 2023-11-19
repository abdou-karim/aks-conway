export interface GameModel {
  id?: string;
  label: string;
  rows: number;
  columns: number;
  grid: boolean[][];
  nextGeneration: boolean[][];
}
