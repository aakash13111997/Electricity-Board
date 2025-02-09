package com.example.ElectricityBoard.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerChartDto;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerDto;
import com.example.ElectricityBoard.entity.ElectricityBoardConsumer;
import com.example.ElectricityBoard.repository.ElectricityBoardConsumerRepository;
import com.example.ElectricityBoard.serviceImplementation.ElectricityServiceImplementation;

@RestController
@CrossOrigin("*")
public class ElectricityController {

	@Autowired
	private ElectricityServiceImplementation boardService;
	
	@Autowired
	private ElectricityBoardConsumerRepository boardRepository;
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Hello");
		return "Hello";
	}
	
	//This endPoint is used for fetching list of connection based on Date Range and applicantID
	@GetMapping(value="/connection",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityBoardConsumerDto>> getConnectionFilter(@RequestParam(required=false) Long applicantId,@RequestParam(required=false) String fromDate,@RequestParam(required=false) String toDate ) throws ParseException{
		List<ElectricityBoardConsumerDto> connectionList=null;
		//Exception Handling
    	try {
		//Service function is called for getting list of connections
		 connectionList=boardService.getConnectionListFilter(applicantId,fromDate,toDate);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return new ResponseEntity<>(connectionList, HttpStatus.OK);
	}
	
	// Save operation
    @PostMapping("/connection")
    public ResponseEntity<ElectricityBoardConsumer> saveConnection(
        @RequestBody ElectricityBoardConsumer connection)
    {
        return new ResponseEntity<ElectricityBoardConsumer>(boardService.saveConnection(connection), HttpStatus.OK);
    }
 
    
    // Update operation
    @PutMapping("/connection/{applicationId}")
    public ResponseEntity<ElectricityBoardConsumer>
    updateConnection(@RequestBody ElectricityBoardConsumer connection,
                     @PathVariable("applicationId") Long applicationId)
    {	
    	ElectricityBoardConsumer updatedConnection=null;
    	//Exception Handling
    	try {
    		//Service function is called for updating the connection
    		updatedConnection=boardService.updateConnection(connection, applicationId);
    	}
    	catch(Exception e) {
    		e.getStackTrace();
    	}
    	if(updatedConnection!=null) {
        	return new ResponseEntity<ElectricityBoardConsumer>(updatedConnection, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<ElectricityBoardConsumer>(updatedConnection, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    @GetMapping(value="/connectionsChart",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityBoardConsumerChartDto>> getConnectionsChartList(@RequestParam(required=false) String status ) throws ParseException{
    	List<ElectricityBoardConsumerChartDto> connectionsChartList=null;
    	//Exception Handling
    	try {
    		//Service function is called for getting number of connection per month
    		connectionsChartList=boardService.getConnectionsChart(status);
    	}
    	catch(Exception e) {
    		e.getStackTrace();
    	}
    	return new ResponseEntity<>(connectionsChartList, HttpStatus.OK);
	}
    
   //This endPoint is used for fetching list of connection based on Date of Application
	@GetMapping(value="/connectionFilter",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ElectricityBoardConsumerDto>> getConnection(@RequestParam(required=false) Long applicantId,@RequestParam(required=false) String dateOfApplication ) throws ParseException{
		List<ElectricityBoardConsumerDto> connectionList=null;
		//Exception Handling
    	try {
    		//Service function is called for getting list of connections based on ApplicantID and Date Of Application
    		connectionList=boardService.getConnectionList(applicantId,dateOfApplication);
    	}
    	catch(Exception e) {
    		e.getStackTrace();
    	}
    	return new ResponseEntity<>(connectionList, HttpStatus.OK);
	}
	
	//test search function ,not getting used in frontend
		@GetMapping(value="/connection2/{applicantId}",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public ResponseEntity<List<ElectricityBoardConsumer>> getConnection2(@PathVariable Long applicantId ) throws ParseException{
			List<ElectricityBoardConsumer> connectionList=null;
			//Exception Handling
	    	try {
	    		//Service function is called for getting list of connections based on ApplicantID only
	    		connectionList=boardRepository.findByApplicantIdAndDateOfApplication(applicantId,null);
			}
			catch(Exception e) {
				e.getStackTrace();
			}
			return new ResponseEntity<List<ElectricityBoardConsumer>>(connectionList, HttpStatus.OK);
		}
		
	
	
	
}
