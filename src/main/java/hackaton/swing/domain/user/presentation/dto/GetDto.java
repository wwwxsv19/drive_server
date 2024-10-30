package hackaton.swing.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class GetDto {
    @Builder
    @Getter
    public static class History {
        private String driverId;
        private String driverCarNumber;
        private int driveCost;
        private int driveDistance;
    }

    @Builder
    @Getter
    public static class HistoryResponse {
        private String driveDate;
        private List<History> historyData;
    }

    @Builder
    @Getter
    public static class KeywordsResponse {
        private List<Integer> keywords;
    }
}
