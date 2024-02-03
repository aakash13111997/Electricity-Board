import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnectionService } from '../connection.service';
import { Chart } from 'chart.js/auto';
import { ConnectionChart } from '../connection-chart';


@Component({
  selector: 'app-view-connection-chart',
  templateUrl: './view-connection-chart.component.html',
  styleUrls: ['./view-connection-chart.component.css']
})
export class ViewConnectionChartComponent implements OnInit{
  //output list
  searchResults: ConnectionChart[];
  //month list
  monthList: string[]=[];
  //number of connections List
  connectionList: number[]=[];
  status:string="";
  //Status Dropdown list
  statusList:string[]=["Pending","Rejected","Approved","Connection Released"];
  myChart:any;
 
  constructor(private connectionService: ConnectionService,
    private router: Router) {}
    title = 'chart';
  ngOnInit(): void {
    this.searchResults=[];
    this.monthList=[];
    this.connectionList=[];
    this.connectionService
      .getConnectionsChartList(this.status)
      .subscribe(results => (this.searchResults = results,
          results.forEach( (element) => {//setting month list here
                                         this.monthList.push(element.month);
                                          //setting number of connections list here
                                         this.connectionList.push(element.connections);
                                        }),
                                        //Initialiizing chart here
                                          this.myChart = new Chart("myChart", {
                                          type: 'bar', //line
                                          data: {
                                                 labels: this.monthList,
                                                 datasets: [{label: 'Number of Connections',
                                                              data:  this.connectionList,
                                                              backgroundColor:"green",
                                                              borderColor: "green",
                                                              borderWidth: 1
                                                            }]
                                                },
                                        options: {scales:{y:{beginAtZero: true}}}}
                                                                    )
                              )
                  );        
  }

  //This function is called when the status gets changed to set the exact value of status removing first 3characters coming from dropdown
  //Eg.status value coming is "0 : Pending" so removing first 3characters to get the eact value "Pending"
  changeStatus(e:any){
    this.status=e.target.value.substring(3);
  }

  //Search function to show chart based on a status value
  search() {
    //Destroying previous canvas to show new chart
    this.myChart.destroy();
    //Routing to ngOnInit function for making the chart
    this.ngOnInit();
  }
  
  //Reset Function
    reset(){
    this.myChart.destroy();
    this.status="";
    this.ngOnInit();
  }


}

