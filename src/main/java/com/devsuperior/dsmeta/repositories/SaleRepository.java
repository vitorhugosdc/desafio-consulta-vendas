package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    /*Na projection tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name fica:
    getId(), getDate(), getAmount(), getName() devido aos nomes no BD, se quiser mudar usar ALIAS (tb_seller.name AS seller_name) ai usa getSellerName()*/
    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller " +
            "ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(tb_seller.name) LIKE CONCAT('%', UPPER(:name), '%')",
            countQuery = "SELECT count(*) " +
                    "FROM tb_sales " +
                    "INNER JOIN tb_seller " +
                    "ON tb_sales.seller_id = tb_seller.id " +
                    "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
                    "AND UPPER(tb_seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    public Page<SaleMinProjection> report(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
}
