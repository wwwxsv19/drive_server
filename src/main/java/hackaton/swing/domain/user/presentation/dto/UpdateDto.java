package hackaton.swing.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class UpdateDto {
    @Getter
    public static class KeywordsRequest {
        private List<Integer> keywords;
    }

    @Builder
    @Getter
    public static class Response {
        private String message;
    }
}
