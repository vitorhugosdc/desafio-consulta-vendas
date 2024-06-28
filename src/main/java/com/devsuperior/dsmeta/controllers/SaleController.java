package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleReportDTO>> getReport(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                         @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                                         @RequestParam(value = "name", defaultValue = "") String name,
                                                         Pageable pageable) {
        Page<SaleReportDTO> result = service.report(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SellerSummaryDTO>> getSummary(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                             @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        List<SellerSummaryDTO> result = service.summary(minDate, maxDate);
        return ResponseEntity.ok(result);
    }

}
