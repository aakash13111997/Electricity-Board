import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConnectionChart } from './connection-chart';
import { Connection } from './connection';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {
  
  //base URL for connection related queries
  private baseUrl = "http://localhost:8085/electricity/connection";
  
  //base URL for chart related queries
  private baseUrlChart = "http://localhost:8085/electricity/connectionsChart";

  constructor(private httpClient: HttpClient) { }
  
  //API get calling for getting the connections
  getConnection(applicantId: number, fromDate: string,toDate: string): Observable<Connection[]> {
    const params = { applicantId, fromDate,toDate};
    return this.httpClient.get<Connection[]>(`${this.baseUrl}`, { params });
  }

  //API get calling for chart functionality 
  getConnectionsChartList(status: string): Observable<ConnectionChart[]> {
    const params = {status};
    return this.httpClient.get<ConnectionChart[]>(`${this.baseUrlChart}`, { params });
  }

  //API put functionality to update the connection
  updateConnection(applicantId: number, connection: Connection): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${applicantId}`, connection);
  }

}
