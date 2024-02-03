package com.example.ElectricityBoard.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.example.ElectricityBoard.dto.ElectricityBoardConsumerChartDto;
import com.example.ElectricityBoard.entity.ElectricityBoardConsumer;

@Repository
public interface ElectricityBoardConsumerRepository extends CrudRepository<ElectricityBoardConsumer,Long>{

		ElectricityBoardConsumer findByApplicantId(Long applicantId);

		List<ElectricityBoardConsumer> findByDateOfApplication(Date dateOfApplication);

		List<ElectricityBoardConsumer> findByApplicantIdAndDateOfApplication(Long applicantId, Date dateOfApplication);

		//Query used for getting Number of Connections for every month based on Month
		@Query(nativeQuery = true, value = "SELECT TO_CHAR(DATE_TRUNC('month',\"date_of_application\"),'Month') as month,\r\n"
				+ "count(*) as connections \r\n"
				+ "FROM \"bcg\".electricity_board_consumer \r\n"
				+ "GROUP BY DATE_TRUNC('month',\"date_of_application\")\r\n"
				+ "order by DATE_TRUNC('month',\"date_of_application\");")
		List<ElectricityBoardConsumerChartDto> findNumberOfConnectionOrderByMonth();

		//Query used for getting Number of Connections for every month based on Month filtered on the basis of Status
		@Query(nativeQuery = true, value = "SELECT TO_CHAR(DATE_TRUNC('month',\"date_of_application\"),'Month') as month,\r\n"
				+ "count(*) as connections \r\n"
				+ "FROM \"bcg\".electricity_board_consumer \r\n"
				+ "where status= :status \r\n"
				+ "GROUP BY DATE_TRUNC('month',\"date_of_application\")\r\n"
				+ "order by DATE_TRUNC('month',\"date_of_application\");")
		List<ElectricityBoardConsumerChartDto> findNumberOfConnectionOrderByMonthByStatus(String status);
		
		//Query used for getting getting list of connections based on date range
		@Query(nativeQuery = true, value = "select *\r\n"
				+ "from \"bcg\".electricity_board_consumer\r\n"
				+ "where \"date_of_application\">= :fromDate \r\n"
				+ "and \"date_of_application\"<= :toDate ;")
		List<ElectricityBoardConsumer> findByApplicantIdAndDateOfApplicationFilter(Date fromDate, Date toDate);


}
