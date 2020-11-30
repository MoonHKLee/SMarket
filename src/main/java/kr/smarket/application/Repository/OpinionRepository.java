package kr.smarket.application.Repository;

import kr.smarket.application.Domain.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findOpinionById(Long id);

    @Query("SELECT o FROM Opinion o " +
            "WHERE o.content LIKE CONCAT('%',:content,'%') ")
    List<Opinion> findOpinionByContent(String content);
}
