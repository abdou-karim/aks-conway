import {Component, OnDestroy, OnInit} from '@angular/core';
import {GameService} from "../../service/game.service";
import {GameModel} from "../../shared/model/GameModel";
import {interval, Subject, Subscription, takeUntil} from "rxjs";
import {CellModel} from "../../shared/model/CellModel";
import {ThemesService} from "../../service/themes-service";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss'],
})
export class GameComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  constructor(private gameService: GameService, public themesService: ThemesService) {
    this.themes = this.themesService.getAvailableThemes();
  }

  gameData?: GameModel;
  gameDatas?: GameModel[] = [];
  themes: string[] = [];
  loadMotif: boolean = false;
  enableToSave: boolean = false;
  speed: number = 100;
  gameSubscription: Subscription | undefined;
  gameInitialise: Subscription | undefined;
  selectedCells: { row: number; col: number }[] = [];
  isGamePaused: boolean = false;
  labelData: string = "";
  gridSize: number = 50;

  ngOnInit(): void {
    this.loadMotif = true;
    this.loadData(this.gridSize);
    this.loadGame()
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.stopGame();
  }

  loadData(gridSize: number): void {
    this.gameInitialise = this.gameService
      .initializeGame(gridSize)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (data) => {
          this.gameData = data;
        },
        (error) => {
          console.error('Error fetching game data', error);
        }
      );
  }

  updateGrid(cellData: CellModel) {
    this.gameService.updateGrid(cellData)
      .pipe(takeUntil(this.destroy$))
      .subscribe(response => {
      })
  }

  updateGeneration(): void {
    this.gameService
      .updateGeneration(this.gameData?.label).pipe(takeUntil(this.destroy$))
      .subscribe(
        (response) => {
          const oldGrid = this.gameData?.grid || [];
          const newGrid = response.grid || [];
          this.updateCellInGrid(oldGrid, newGrid)
          this.gameData = response;

        },
        (error) => {
          console.error('Error updating generation', error);
        }
      );
  }

  onClickCell(row: number, col: number): void {
    const index = this.selectedCells.findIndex((cell) => cell.row === row && cell.col === col);

    if (index !== -1) {
      // La cellule est déjà sélectionnée, la déselectionner
      this.selectedCells.splice(index, 1);
      this.updateGrid({row: row, col: col, alive: false})
       this.selectedCells.length >= 1 ? this.loadMotif = false: this.loadMotif = true;
    } else {
      this.updateGrid({row: row, col: col, alive: true})
      // La cellule n'est pas sélectionnée, l'ajouter à la liste des cellules sélectionnées
      this.selectedCells.push({row, col});
      this.loadMotif = false;
    }
  }

  isSelected(row: number, col: number): boolean {
    return this.selectedCells.some((cell) => cell.row === row && cell.col === col);
  }


  onClickStart() {
    if (!this.isGamePaused || this.gameInitialise) {
      this.stopGame();
      this.gameInitialise?.unsubscribe();
    }
    this.gameSubscription = interval(this.speed).subscribe(() => {
      this.updateGeneration()
    })
    this.isGamePaused = false;
    this.enableToSave = false;
  }

  onClickStop() {
    this.stopGame();
    this.isGamePaused = true;
    this.enableToSave = true;
  }

  onSpeedChange(): void {
    console.log(this.speed)
  }

  clearGrid() {

    this.selectedCells = [];
    this.loadData(this.gridSize);
    this.stopGame();
    this.loadMotif = true;
  }

  private stopGame() {
    if (this.gameSubscription) {
      this.gameSubscription.unsubscribe();
    }
  }

  saveGame(game: undefined | GameModel) {
    if (this.labelData && game) {
      const dataShared = {
        label: this.labelData,
        rows: game.rows,
        columns: game.columns,
        grid: game.grid,
        nextGeneration: game.nextGeneration
      }
      this.gameService
        .saveGame(dataShared)
        .pipe(takeUntil(this.destroy$))
        .subscribe(
          (response) => {
            this.labelData = "";
          },
          (error) => {
            console.error('Error creating game', error);
          }
        );
    } else {
      console.error('Invalid labelData or game');
    }
  }


  loadGame() {
    this.gameService
      .loadGame()
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (data) => {

          this.gameDatas = data.filter(game => game.label != "INITIALE");
        },
        (error) => {
          console.error('Error fetching game data', error);
        }
      );
  }


  onGameSelect(value: any) {
    this.loadData(this.gridSize)
    this.gameService
      .loadGameByName(value.target.value)
      .pipe(takeUntil(this.destroy$))
      .subscribe(
        (data) => {
          const oldGrid = this.gameData?.grid || [];
          const newGrid = data.grid || [];
         this.updateCellInGrid(oldGrid,newGrid)
          this.gameData = data;
          this.loadMotif = true;
        },
        (error) => {
          console.error('Error fetching game data', error);
        }
      );

  }

  updateCellInGrid(oldGrid: boolean[][], newGrid: boolean[][]) {

    // Réinitialiser les cellules sélectionnées à chaque génération
    this.selectedCells = [];

    // Vérifier les changements d'état des cellules
    for (let i = 0; i < oldGrid.length; i++) {
      for (let j = 0; j < oldGrid[i].length; j++) {
        if (oldGrid[i][j] !== newGrid[i][j]) {
          // La cellule a changé d'état, ajoutez-la à la liste des cellules sélectionnées
          this.selectedCells.push({row: i, col: j});
        }
      }
    }
  }


  updateSize() {
    if (this.gridSize < 50) {
      this.gridSize = 50;
      alert("Doit etre superieur a 50")
      return;
    }
    this.loadData(this.gridSize);
  }


  onThemeChange(selectedTheme: any): void {
    this.themesService.setTheme(selectedTheme.target.value);
  }
}

