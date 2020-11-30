package kr.smarket.application.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class OpinionResponse {
    Long id;
    String content;
    String userName;
}
