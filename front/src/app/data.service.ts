import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class DataService {

  private REST_API_SERVER = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) {}

  public sendGetAllRequest(): Observable<any>{
    return this.httpClient.get(this.REST_API_SERVER + 'currency/all');
  }

  public sendGetOneRequest(currencyName: string): Observable<any>{
    return this.httpClient.get(this.REST_API_SERVER + 'currency/' + currencyName);
  }

  public sendDeleteRequest(currencyName: string): Observable<any> {
    const url = this.REST_API_SERVER + 'currency/delete/' + currencyName;
    return this.httpClient.delete(url);
  }

  public sendPostRequest(currencyName: string, currencyValue: string) {
    const url = this.REST_API_SERVER + 'currency/add';
    const body = {
      name: currencyName,
      currency_value: currencyValue
    };
    const headers = { 'Content-Type': 'application/json' };
    return this.httpClient.post(this.REST_API_SERVER + 'currency/add',
     body, {headers: new HttpHeaders(headers)});
  }
}
