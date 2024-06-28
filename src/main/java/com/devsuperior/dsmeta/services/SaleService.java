package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SellerSummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.LocalDateParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<SaleReportDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate max = LocalDate.now();
        LocalDate min = max.minusYears(1);

        max = verifyDate(maxDate, max);
        min = verifyDate(minDate, min);

        Page<SaleReportProjection> result = repository.report(min, max, name, pageable);
        return result.map(SaleReportDTO::new);
    }

    @Transactional(readOnly = true)
    public List<SellerSummaryDTO> summary(String minDate, String maxDate) {
        LocalDate max = LocalDate.now();
        LocalDate min = max.minusYears(1);

        max = verifyDate(maxDate, max);
        min = verifyDate(minDate, min);

        List<SellerSummaryProjection> result = repository.summary(min, max);
        return result.stream().map(SellerSummaryDTO::new).toList();
    }

    private LocalDate verifyDate(String dateStr, LocalDate defaultDate) {
        try {
            if (!dateStr.isBlank() && dateStr.length() == 10) {
                defaultDate = LocalDate.parse(dateStr);
            }
            return defaultDate;
        } catch (DateTimeParseException e) {
            throw new LocalDateParseException("Invalid date");
        }
    }
}
