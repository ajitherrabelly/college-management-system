// src/app/student-table/student-table.component.ts
import { Component, OnInit } from '@angular/core';
import { StudentService } from '../services/student.service';
import { MatTableDataSource } from '@angular/material/table';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-student-table',
  templateUrl: './student-table.component.html',
  styleUrls: ['./student-table.component.css']
})
export class StudentTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'address', 'city', 'state', 'zipcode', 'createdAt'];
  dataSource = new MatTableDataSource<any>();

  constructor(private studentService: StudentService, private datePipe: DatePipe) {}

  ngOnInit(): void {
    this.fetchStudents();
  }

  fetchStudents(): void {
    this.studentService.getStudents().subscribe(data => {
      this.dataSource.data = data;
    });
  }

  formatDate(date: string): string {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm:ss')!;
  }
}
