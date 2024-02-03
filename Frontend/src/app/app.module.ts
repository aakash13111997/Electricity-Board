import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { ViewConnectionComponent } from './view-connection/view-connection.component';
import { UpdateConnectionComponent } from './update-connection/update-connection.component';
import { ViewConnectionChartComponent } from './view-connection-chart/view-connection-chart.component';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';

const routes: Routes = [
  //Setting the Endpoints here for routing
  { path: '', component: ViewConnectionComponent },
  { path: 'viewconnection', component: ViewConnectionComponent },
  { path: 'updateconnection/:id', component: UpdateConnectionComponent},
  { path: 'updateconnectionchart', component: ViewConnectionChartComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    ViewConnectionComponent,
    UpdateConnectionComponent,
    ViewConnectionChartComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    FormsModule,
    BrowserModule,
    Ng2SearchPipeModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    PaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
