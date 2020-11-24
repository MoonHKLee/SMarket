package kr.smarket.application.DTO.Response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;

    private String productName;

    private String marketName;

    private Float weight;

    private Integer price;

    private String userName;

    private String phoneNumber;

    private String storeName;

    private String content;

    private String src;
}
