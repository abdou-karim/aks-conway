import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map, catchError, tap} from 'rxjs/operators';
import {GameModel} from "../shared/model/GameModel";
import {Observable, throwError} from "rxjs";
import {CellModel} from "../shared/model/CellModel";

@Injectable({
  providedIn: 'root'
})
export class GameService {
  apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  initializeGame(gridSize: number): Observable<GameModel> {
    return this.http.get<GameModel>(this.apiUrl + '/game/initializeGrid?gridSize=' + gridSize)
      .pipe(map((game: GameModel) => {
        return game;
      }));
  }

  updateGrid(cellData?: CellModel) {
    return this.http.put<CellModel>(this.apiUrl + '/game/update/grid', cellData)
      .pipe(
        map(response => response)
      );
  }

  updateGeneration(gameName: string | undefined): Observable<GameModel> {
    return this.http.get<GameModel>(`${this.apiUrl}/game/updateGeneration/${gameName}`);
  }

  saveGame(gameModel: GameModel | undefined): Observable<GameModel> {
    return this.http.post<GameModel>(`${this.apiUrl}/game/save`, gameModel);
  }

  loadGame(): Observable<GameModel[]> {
    return this.http.get<GameModel[]>(this.apiUrl + '/game/load')
      .pipe(map((games: GameModel[]) => {
        return games;
      }));
  }

  loadGameByName(gameName: string): Observable<GameModel> {
    return this.http.get<GameModel>(this.apiUrl + '/game/name/' + gameName)
      .pipe(map((games: GameModel) => {
        return games;
      }));
  }

}
