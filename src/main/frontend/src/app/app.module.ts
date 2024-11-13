// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { DatePipe } from '@angular/common';
import { AppComponent } from './app.component';
import { StudentTableComponent } from './student-table/student-table.component';
import { StudentService } from './services/student.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    StudentTableComponent
  ],
  imports: [
    BrowserModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpClientModule
  ],
  providers: [StudentService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
