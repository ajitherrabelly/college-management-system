// src/app/services/student.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl = `${environment.apiUrl}/students`;

  constructor(private http: HttpClient) { }

  getStudents(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
