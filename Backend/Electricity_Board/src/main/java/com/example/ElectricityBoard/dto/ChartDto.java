package com.example.ElectricityBoard.dto;

import lombok.Data;

@Data
public class ChartDto implements ElectricityBoardConsumerChartDto{
	

	private String month;
	
	private Long connections;
	
	public Long getConnections(){
		return connections;
	}

	public void setConnections(Long connections) {
		this.connections = connections;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
