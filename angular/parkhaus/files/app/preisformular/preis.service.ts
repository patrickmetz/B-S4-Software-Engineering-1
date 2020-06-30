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
export class PreisService {
  private getUrl: string = 'http://localhost:8080/ParkhausServlet?cmd=PreiseZeigen';
  private postUrl: string = 'http://localhost:8080/ParkhausServlet?cmd=PreiseSpeichern';

  private postOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
  };

  constructor(private http: HttpClient) {
  }

  gibPreise(): Observable<PreisIF[]> {
    return this.http.get<PreisIF[]>(this.getUrl)
      .pipe(
        catchError(this.behandleFehler<PreisIF[]>('gibPreise', []))
      );
  }

  speicherePreise(preise: PreisIF[]): Observable<PreisIF[]> {
    return this.http.post<PreisIF[]>(this.postUrl, this.preiseZuPostBody(preise), this.postOptions).pipe(
      catchError(this.behandleFehler<PreisIF[]>('speicherePreise'))
    );
  }

  private preiseZuPostBody(preise: PreisIF[]) {
    let postBody = [];

    preise.forEach(function (preis) {
      postBody.push(
        encodeURIComponent(preis.typ) + "=" + encodeURIComponent(preis.betrag)
      );
    });

    return postBody.join("&");
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
