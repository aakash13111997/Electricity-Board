import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnectionService } from '../connection.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Connection } from '../connection';

@Component({
  selector: 'app-update-connection',
  templateUrl: './update-connection.component.html',
  styleUrls: ['./update-connection.component.css']
})
export class UpdateConnectionComponent implements OnInit {

  applicantId: number=0;
  connection: Connection = new Connection();
  searchResults: Connection[];
  dateOfApplicationn: Date;
  dateOfApproval: string|null;
  prevLoadApplied: number=0;
  status:any;
  gender:any;
  ownership:any;
  category:any;
  statusList: string[]=["Pending","Approved","Rejected","Connection Released"];
  genderList: string[]=["Male","Female"];
  ownershipList: string[]=["INDIVIDUAL","JOINT"];
  categoryList: string[]=["Commerical","Residential"];
  
  constructor(private connectionService: ConnectionService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    //Setting the connection to new Connection just before updation
    this.connection=new Connection();
    //Receving the Applicant ID value via route
    this.applicantId = this.route.snapshot.params['id'];
    //Calling GET API to get connection detail based on application details
    this.connectionService.getConnection(this.applicantId,"","").subscribe(data => {
      //Since our backend API send list of connection so taking out the one and only first connection and populating it on the page
      this.connection = data[0];
      //Getting all the dropdown values to poulate it on the page
      this.prevLoadApplied=this.connection.loadApplied;
      this.status=this.connection.status;
      this.gender=this.connection.gender;
      this.category=this.connection.category;
      this.ownership=this.connection.ownership;
    }, error => console.log(error) );
  }
  
  onSubmit() {

    //calling the update function and checking the response for giving user the proper output.
    this.connectionService.updateConnection(this.applicantId, this.connection).subscribe(data => {
      this.updateConnection(this.applicantId);
      window.alert("Connection Successfully Updated.");
    }
    ,err => {
          if( err instanceof HttpErrorResponse ) {
            if (err.status === 400) {
              window.alert("Connection Not Updated!!!!");
              this.updateConnection(this.applicantId);
            }
          }
          if( err instanceof HttpResponse ) {
            if (err.status === 200) {
              setTimeout(() => {
                window.prompt("Connection Successfully Updated.");
                }, 200);
                  }
          }
        });
    //Calling the routing function to route to same page after updating all the details again
    this.updateConnection(this.applicantId);
  }

  //This function is routing the same page again after refreshing the page
  updateConnection(id: number) {
    this.router.navigate(['updateconnection', id]);
  }

  //This function is validating if the loadApplied is not more than 200kV and if yes it is giving an alert
  checkValidLoadApplied(){
    if(this.connection.loadApplied>200){
      this.connection.loadApplied= this.prevLoadApplied;
      window.alert("Load Applied should not be more than 200kV!!!");
    }
    return true;
  }

  //This function is called when the status gets changed to set the exact value of status removing first 3characters coming from drpodown
  //Eg.status value coming is "0 : Pending" so removing first 3characters to get the exact value "Pending"
  //The same thing is used in all the 4 functions below
  changeStatus(e:any){
    this.status=e.target.value;
    this.connection.status=this.status.substring(3);
  }

  changeGender(e:any){
    this.gender=e.target.value;
    this.connection.gender=this.gender.substring(3);
  }

  changeOwnership(e:any){
    this.ownership=e.target.value;
    this.connection.ownership=this.ownership.substring(3);
  }

  changeCategory(e:any){
    this.category=e.target.value;
    this.connection.category=this.category.substring(3);
  }

}


