import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

import {PreisIF} from "../preisIF";

@Injectable({
  providedIn: 'root'
})
export class PreisService {
  private getUrl: string = 'http://localhost:8080/ParkhausServlet?cmd=PreiseZeigen';

  constructor(private http: HttpClient) { }

  gibPreise(): Observable<PreisIF[]> {
    return this.http.get<PreisIF[]>(this.getUrl)
      .pipe(
        tap(_ => console.log('Preise geholt')),
        catchError(this.behandleFehler<PreisIF[]>('gibPreise', []))
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
