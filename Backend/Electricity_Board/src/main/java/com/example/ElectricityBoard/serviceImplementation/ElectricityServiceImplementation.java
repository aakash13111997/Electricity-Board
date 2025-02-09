package com.example.ElectricityBoard.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerChartDto;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerDto;
import com.example.ElectricityBoard.entity.ElectricityBoardConsumer;
import com.example.ElectricityBoard.repository.ElectricityBoardConsumerRepository;
import com.example.ElectricityBoard.service.ElectricityService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import io.micrometer.common.util.StringUtils;
import java.text.DateFormat;  

@Service
public class ElectricityServiceImplementation implements ElectricityService {

	@Autowired
	private ElectricityBoardConsumerRepository electricityBoardConsumerRepository;
	
	//Function for Getting connections list with range of date of application
	@Override
	public List<ElectricityBoardConsumerDto> getConnectionListFilter(Long applicantId, String fromDate,String toDate) throws ParseException{
		List<ElectricityBoardConsumer> connectionList=new ArrayList<>();
		//Exception Handling
    	try {
    		//Applicant ID will be used only if either of the date or both the dates are not shared
    		if((applicantId!=null &&applicantId>0) && (StringUtils.isEmpty(fromDate)||StringUtils.isEmpty(toDate)) ) {
    			connectionList.add(electricityBoardConsumerRepository.findByApplicantId(applicantId));
    		}
    		//Applicant ID will be ignored if a date range is provided
    		else if(!StringUtils.isEmpty(fromDate)&&!StringUtils.isEmpty(toDate )) {
    			//Addition of 05:30 to equate it with the DB data as the input date is in string
    			fromDate=fromDate+" 05:30:00";
    			toDate=toDate+" 05:30:00";
    			//Converting input date string value to Date using parse function
    			Date fromDateValue=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromDate);
    			Date toDateValue=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toDate);
    			connectionList=electricityBoardConsumerRepository.findByApplicantIdAndDateOfApplicationFilter(fromDateValue,toDateValue);
    		}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
		// Mapper Function for converting Entities to DTOs
		List<ElectricityBoardConsumerDto> connectionDtoList=getConnectionDtoList(connectionList);
		return connectionDtoList;
	}

	//function for saving the connection in case create page is needed
	@Override
	public ElectricityBoardConsumer saveConnection(ElectricityBoardConsumer connection) {
		electricityBoardConsumerRepository.save(connection);
		return connection;
	}
	
	//function for updating the connection
	@Override
	public ElectricityBoardConsumer updateConnection(ElectricityBoardConsumer connection, Long applicationId) {
		//Exception Handling
    	try {
    	ElectricityBoardConsumer realConnection=electricityBoardConsumerRepository.findByApplicantId(applicationId);
		//Assignment Validations are fulfilled
		//1.Date of Application should not be changed
		//2.GovtIDType,IDNumber,ApplicantID cannot be changed
		//3.Load Value can not be more than 200kV
		if(!compareDate(realConnection.getDateOfApplication(),connection.getDateOfApplication()) || !realConnection.getGovtIdType().equalsIgnoreCase(connection.getGovtIdType()) || !realConnection.getIdNumber().equalsIgnoreCase(connection.getIdNumber())|| realConnection.getApplicantId()!=connection.getApplicantId() || Long.parseLong(connection.getLoadApplied())>200) {
			return null;
		}
		electricityBoardConsumerRepository.save(connection);}
		catch(Exception e) {
			e.getStackTrace();
		}
		return connection;
	}

	//mapper function
	private List<ElectricityBoardConsumerDto> getConnectionDtoList(List<ElectricityBoardConsumer> connectionList) {
		List<ElectricityBoardConsumerDto> dtoList=new ArrayList<>();
		//mapping entity to the dto
		
		for(ElectricityBoardConsumer connection:connectionList) {
			//Exception Handling
	    	try {
			ElectricityBoardConsumerDto dto=new ElectricityBoardConsumerDto();
			dto.setApplicantId(connection.getApplicantId());
			dto.setApplicantName(connection.getApplicantName());
			dto.setCategory(connection.getCategory());
			dto.setDateOfApplication(dateCheck(connection.getDateOfApplication()));
			dto.setDateOfApproval(dateCheck(connection.getDateOfApproval()));
			dto.setDistrict(connection.getDistrict());
			dto.setGender(connection.getGender());
			dto.setGovtIdType(connection.getGovtIdType());
			dto.setIdNumber(connection.getIdNumber());
			dto.setLoadApplied(Long.parseLong(connection.getLoadApplied()));
			dto.setModifiedDate(dateCheck(connection.getModifiedDate()));
			dto.setOwnership(connection.getOwnership());
			dto.setPincode(connection.getPincode());
			dto.setReviewerComments(connection.getReviewerComments());
			dto.setReviewerId(connection.getReviewerId());
			dto.setReviewerName(connection.getReviewerName());
			dto.setState(connection.getState());
			dto.setStatus(connection.getStatus());
			dtoList.add(dto);}
			catch(Exception e) {
				e.getStackTrace();
			}
		}
		return dtoList;
	}
	
	//Function for Getting connections list with date of application without range
	@Override
	public List<ElectricityBoardConsumerDto> getConnectionList(Long applicantId, String applicationDate) throws ParseException{
		List<ElectricityBoardConsumer> connectionList=new ArrayList<>();
		//Exception Handling
    	try {
    		//Applicant ID will be used only if the date of application is not given
    		if((applicantId!=null &&applicantId>0) && StringUtils.isEmpty(applicationDate)) {
    			connectionList.add(electricityBoardConsumerRepository.findByApplicantId(applicantId));
    		}

    		//date of application will be used only if Applicant ID is not given
    		else if((applicantId==null || applicantId<=0) && !StringUtils.isEmpty(applicationDate)) {
    			//Addition of 05:30 to equate it with the DB data as the input date is in string
    			applicationDate=applicationDate+" 05:30:00";
    			//Converting input date string value to Date using parse function
    			Date dateOfApplication=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(applicationDate);
    			connectionList=electricityBoardConsumerRepository.findByDateOfApplication(dateOfApplication);
    		}
    		//date of application and Applicant ID will be used
    		else {
    			//Addition of 05:30 to equate it with the DB data as the input date is in string
    			applicationDate=applicationDate+" 05:30:00";
    			//Converting input date string value to Date using parse function
    			Date dateOfApplication=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(applicationDate);
    			connectionList=electricityBoardConsumerRepository.findByApplicantIdAndDateOfApplication(applicantId,dateOfApplication);
    		}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		// Mapper Function for converting Entities to DTOs
		List<ElectricityBoardConsumerDto> connectionDtoList=getConnectionDtoList(connectionList);
		return connectionDtoList;
	}
	
	//Function used for getting chart based on Number of Connections per Month
	@Override
	public List<ElectricityBoardConsumerChartDto> getConnectionsChart(String status) {
		List<ElectricityBoardConsumerChartDto> connectionsChartDtoList=new ArrayList<>();
		//Exception Handling
    	try {
    		if(StringUtils.isEmpty(status)) {
    			connectionsChartDtoList=electricityBoardConsumerRepository.findNumberOfConnectionOrderByMonth();
    		}
    		else {
    			connectionsChartDtoList=electricityBoardConsumerRepository.findNumberOfConnectionOrderByMonthByStatus(status);
			
    		}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return connectionsChartDtoList;
	}
	
	//Validating Date
	private String dateCheck(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		if(date==null) {
			return "";
		}
		else {
			return dateFormat.format(date);
		}
	}
	
	//Comparing Date for the updation -used in update function
	private Boolean compareDate(Date date1,Date date2) {
		if(date1.getDay()!=date2.getDay()) {
			return false;
		}
		else if(date1.getMonth()!=date2.getMonth()) {
			return false;
		}
		else if(date1.getYear()!=date2.getYear()) {
			return false;
		}
		return true;
	}

	
	
}
