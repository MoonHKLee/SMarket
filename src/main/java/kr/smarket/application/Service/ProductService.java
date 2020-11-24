package kr.smarket.application.Service;

import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.Domain.BusinessMember;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product registerProduct(RegisterProductRequest request, BusinessMember member, String src) {
        return productRepository.save(
                Product.builder()
                        .content(request.getContent())
                        .member(member)
                        .price(Integer.parseInt(request.getPrice()))
                        .productName(request.getProductName())
                        .src(src)
                        .weight(Float.parseFloat(request.getWeight()))
                        .build()
        );
    }

    public List<Product> getAllProductList() {
        return productRepository.findAll();
    }
}
