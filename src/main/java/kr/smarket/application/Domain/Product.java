package kr.smarket.application.Domain;

import kr.smarket.application.Domain.Common.LogDateTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends LogDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String productName;

    private Float weight;

    private Integer price;

    private String content;

    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private BusinessMember member;
}
