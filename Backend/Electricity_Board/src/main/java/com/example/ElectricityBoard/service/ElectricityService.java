package com.example.ElectricityBoard.service;

import java.text.ParseException;
import java.util.List;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerChartDto;
import com.example.ElectricityBoard.dto.ElectricityBoardConsumerDto;
import com.example.ElectricityBoard.entity.ElectricityBoardConsumer;

public interface ElectricityService {

	public List<ElectricityBoardConsumerChartDto> getConnectionsChart(String status);

	public ElectricityBoardConsumer saveConnection(ElectricityBoardConsumer connection);

	public ElectricityBoardConsumer updateConnection(ElectricityBoardConsumer connection, Long applicationId);

	public List<ElectricityBoardConsumerDto> getConnectionListFilter(Long applicantId, String applicationDate,
			String applicationDate2) throws ParseException;
	
	public List<ElectricityBoardConsumerDto> getConnectionList(Long applicantId, String applicationDate) throws ParseException;
		
}
