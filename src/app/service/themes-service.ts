import {Injectable} from '@angular/core';
import {Theme} from "../shared/model/Theme";

@Injectable({
  providedIn: 'root'
})
export class ThemesService {
  private themes: { [key: string]: Theme } = {
    light: {
      name: 'Light',
      headerBackground: "#343a40",
      headerTexte: "#f8f9fa",
      backgroundGeneral: "#f8f9fa",
      backgroundGrille: "#e5e9ec",
      backgroundCelluleMorte: "#e5e9ec",
      backgroundBorderCellule:'#007bff',
      backgroundCelluleVivante: "#343a40",
      backgroundBouton: "#f8f9fa",
      boutonTexte: "#212529",
      boutonHover: "#0056b3"
    },
    dark: {
      name: 'dark',
      headerBackground: '#343a40',
      headerTexte: '#ffffff',
      backgroundGeneral: '#495057',
      backgroundGrille: '#212529',
      backgroundCelluleMorte: '#212529',
      backgroundBorderCellule:'#28a745',
      backgroundCelluleVivante: '#ffffff',
      backgroundBouton: '#28a745',
      boutonTexte: '#212529',
      boutonHover: '#0056b3'
    },
  }


  currentTheme: Theme = this.themes['dark'];

  getAvailableThemes(): string[] {
    return Object.keys(this.themes);
  }

  setTheme(themeName: string): void {
    if (this.themes[themeName]) {
      this.currentTheme = this.themes[themeName];
    }
  }
}
