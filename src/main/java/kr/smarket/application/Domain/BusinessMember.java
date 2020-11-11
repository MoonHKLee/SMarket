package kr.smarket.application.Domain;

import kr.smarket.application.Domain.Enum.Category;
import lombok.*;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class BusinessMember extends Member {

    private String marketName;

    private String officeName;

    private Category category;

    private String officeNumber;
}
