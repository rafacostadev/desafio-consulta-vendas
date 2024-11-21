package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getSaleReport(Pageable pageable, String minDate, String maxDate, String name) {
		LocalDate formattedMinDate = null;
		LocalDate formattedMaxDate = null;
		if (!maxDate.isEmpty()) {
			formattedMaxDate = LocalDate.parse(maxDate);
		} else {
			formattedMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		if (!minDate.isEmpty()) {
			formattedMinDate = LocalDate.parse(minDate);
		} else {
			formattedMinDate = formattedMaxDate.minusYears(1L);
		}

		return repository.getSaleReport(pageable, formattedMinDate, formattedMaxDate, name);
	}

	public Page<SummaryDTO> getSummary(Pageable pageable, String minDate, String maxDate) {
		LocalDate formattedMinDate = null;
		LocalDate formattedMaxDate = null;
		if (!maxDate.isEmpty()) {
			formattedMaxDate = LocalDate.parse(maxDate);
		} else {
			formattedMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		if (!minDate.isEmpty()) {
			formattedMinDate = LocalDate.parse(minDate);
		} else {
			formattedMinDate = formattedMaxDate.minusYears(1L);
		}
		return repository.getSummary(pageable, formattedMinDate, formattedMaxDate);
	}
}
