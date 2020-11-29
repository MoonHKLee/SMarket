package kr.smarket.application.Repository;

import kr.smarket.application.Domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT p FROM Product p " +
            "WHERE p.member.marketName LIKE CONCAT('%',:marketName,'%') ")
    Page<Product> findAllByMarketName(String marketName, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE p.productName LIKE CONCAT('%',:productName,'%') ")
    Page<Product> findAllByProductName(String productName, Pageable pageable);

    Product findProductById(Long id);
}
