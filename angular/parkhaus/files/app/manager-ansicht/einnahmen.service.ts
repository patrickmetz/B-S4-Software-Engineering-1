import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

import {PreisIF} from "../preisIF";

/**
 * @author Patrick Metz
 */
@Injectable({
  providedIn: 'root'
})
export class EinnahmenService {
  private getUrlEinnahmenTag: string = 'http://localhost:8080/ParkhausServlet?cmd=ManagersichtTagesEinnahmen';
  private getUrlEinnahmenJahr: string = 'http://localhost:8080/ParkhausServlet?cmd=ManagersichtJahresEinnahmen';

  constructor(private http: HttpClient) {
  }



  gibTagesEinnahmen(): Observable<string> {
    return this.http.get(this.getUrlEinnahmenTag,{responseType: 'text'})
      .pipe(
        catchError(this.behandleFehler<string>('gibEinnahmenTag', ""))
      );

  }

  gibJahresEinnahmen(): Observable<string> {
    return this.http.get(this.getUrlEinnahmenJahr,{responseType: 'text'})
      .pipe(
        catchError(this.behandleFehler<string>('gibEinnahmenJahr', ""))
      );

  }

  private behandleFehler<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      console.log(`${operation} failed: ${error.message}`);

      //App läuft weiter, mit leerem Ergebnis vom richtigen Typ
      return of(result as T);
    };
  }
}
