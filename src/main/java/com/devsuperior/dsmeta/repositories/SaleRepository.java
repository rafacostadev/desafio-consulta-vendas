package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(sale.id, sale.date, sale.amount, sale.seller.name) FROM Sale sale "
			+ "WHERE sale.date BETWEEN :minDate AND :maxDate AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%')	)")
	Page<SaleReportDTO> getSaleReport(Pageable pageable, LocalDate minDate, LocalDate maxDate, String name);

	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(sale.seller.name , SUM(sale.amount)) FROM Sale sale "
			+ "WHERE sale.date BETWEEN :minDate AND :maxDate GROUP BY sale.seller.name")
	Page<SummaryDTO> getSummary(Pageable pageable, LocalDate minDate, LocalDate maxDate);
}
