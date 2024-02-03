import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnectionService } from '../connection.service';
import { Connection } from '../connection';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import { formatDate } from '@angular/common';


@Component({
  selector: 'app-view-connection',
  templateUrl: './view-connection.component.html',
  styleUrls: ['./view-connection.component.css'] 
})
export class ViewConnectionComponent implements OnInit{
  //filetr data below
  applicantId: number=0;
  fromDate: string="";
  toDate: string="";
  //output array
  searchResults: Connection[]=[];
  //initializing column values
  cols:any[];
  zeroFlag:Boolean=false;

  constructor(private connectionService: ConnectionService,
    private router: Router) {}
  ngOnInit(): void {
    //this flag is used to find if any record exist or not after the search
    this.zeroFlag=false;
    //initializing column field and headers here
    this.cols=[{field: 'applicantId' , header: 'Applicant ID'},
    {field:'applicantName',header:'Applicant Name'},
    {field:'gender',header:'Gender'},
    {field:'district',header:'District'},
    {field:'state',header:'State'},
    {field:'pincode',header:'Pincode'},
    {field:'ownership',header:'Ownership'},
    {field:'govtIdType',header:'Govt ID Type'},
    {field:'idNumber',header:'ID Number'},
    {field:'category',header:'Category'},
    {field:'loadApplied',header:'Load Applied'},
    {field:'status',header:'Status'},
    {field:'reviewerId',header:'Reviewer ID'},
    {field:'reviewerName',header:'Reviewer Name'},
    {field:'reviewerComments',header:'Reviewer Comments'},
    {field:'dateOfApplication',header:'Date Of Application'},
    {field:'dateOfApproval',header:'Date Of Approval'}
    ]
  }
  search(): void {
    //reseting the output values and flags before a fresh search
    this.searchResults=[];
    this.zeroFlag=false;
    //if applicantID is null,setting it to 0 as long cannot have a null value
    if(this.applicantId==null){
      this.applicantId=0;
    }
    //validating if any one of the date column is not blank as range values should contain both the dates
    if((this.applicantId<=0 && this.toDate=="" && this.fromDate!="") || (this.applicantId<=0 && this.toDate!="" && this.fromDate=="")){
      window.alert("Please select both the dates From Date and To Date for getting the Required Output!!!!");
    }
    //calling the api to find the required data
    this.connectionService
      .getConnection(this.applicantId, this.fromDate,this.toDate)
      .subscribe(results => (this.searchResults = results,this.searchResults.length==0?this.zeroFlag=true:false));
  }

  //routing to update page after user clicks on a particular applicantId
  updateConnection(id: number) {
    this.router.navigate(['updateconnection', id]);
  }

  //routing to connection chart page after user clicks on a Connection Chart button
  viewConnectionChart() {
    this.router.navigate(['updateconnectionchart']);
  }

  //Function to check whether From Date is not higher than To Date
  checkValidFromDate(){
    if(this.toDate!="" && this.fromDate!=""){
      //Converting Dates from String to Date variable for comparison
      let from = formatDate(this.fromDate,'yyyy-MM-dd','en_US');
      let to  = formatDate(this.toDate,'yyyy-MM-dd','en_US');
      //Giving an alert if the validation gets failed
      if(from>to){
        this.fromDate= "";
        window.alert("From Date can not be higher than To Date!!!!");
       }      
    }
    return true;
  }
  
  //Function to check whether To Date is not lessed than From Date
  checkValidToDate(){
    if(this.toDate!="" && this.fromDate!=""){
      //Converting Dates from String to Date variable for comparison
      let from = formatDate(this.fromDate,'yyyy-MM-dd','en_US');
      let to  = formatDate(this.toDate,'yyyy-MM-dd','en_US');
      //Giving an alert if the validation gets failed
      if(from>to){
        this.toDate= "";
        window.alert("To Date can not be less than From Date!!!!");
       }      
    }
    return true;
  }

  //Reset Function
  reset(){
    this.searchResults=[];
    this.applicantId=0;
    this.fromDate="";
    this.toDate="";
    this.zeroFlag=false;
  }
  
}
