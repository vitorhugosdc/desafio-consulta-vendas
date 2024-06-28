package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleReportDTO() {
    }

    public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SaleReportDTO(SaleReportProjection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.amount = projection.getAmount();
        this.sellerName = projection.getSellerName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
