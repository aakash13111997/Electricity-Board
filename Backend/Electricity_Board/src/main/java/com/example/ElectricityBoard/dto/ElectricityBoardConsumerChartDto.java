package com.example.ElectricityBoard.dto;

import lombok.Data;

@Data
public interface ElectricityBoardConsumerChartDto {
	

	public Long getConnections(); 
	public String getMonth();
}
