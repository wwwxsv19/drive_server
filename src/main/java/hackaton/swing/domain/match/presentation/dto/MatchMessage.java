package hackaton.swing.domain.match.presentation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MatchMessage {
    private String matchRoomId;
    private String fromPlace; // 출발 지역
    private String toPlace; // 도착 지역

    private List<Integer> keywords;
}