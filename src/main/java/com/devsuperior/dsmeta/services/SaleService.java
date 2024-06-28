package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.LocalDateParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    public Page<SaleMinDTO> report(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate max = LocalDate.now();
        LocalDate min = max.minusYears(1);
        try {
            if (!maxDate.isBlank() && maxDate.length() == 10) {
                max = LocalDate.parse(maxDate);
            }
            if (!minDate.isBlank() && minDate.length() == 10) {
                min = LocalDate.parse(minDate);
            }
        } catch (DateTimeParseException e) {
            throw new LocalDateParseException("Invalid date");
        }
        Page<SaleMinProjection> result = repository.report(min, max, name, pageable);
        return result.map(SaleMinDTO::new);
    }
}
