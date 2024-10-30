package hackaton.swing.domain.drive.service;

import hackaton.swing.domain.drive.DriveHis;
import hackaton.swing.domain.drive.repository.DriveHisRepository;
import hackaton.swing.domain.user.presentation.dto.GetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DriveService {
    private final DriveHisRepository driveHisRepository;

    @Transactional
    public List<GetDto.HistoryResponse> getDriveHis() {
        log.info("데이터 변환 시작 : DriveHis 리스트 찾기");
        List<DriveHis> driveHisList = driveHisRepository.findAllByPassengerId("test");

        log.info("Map 형식으로 날짜 그룹핑");
        Map<String, List<GetDto.History>> groupedByDate = driveHisList.stream()
                .collect(Collectors.groupingBy(
                        DriveHis::getCreatedTime,
                        Collectors.mapping(drive -> GetDto.History.builder()
                                        .driverId(drive.getDriverId())
                                        .driverCarNumber(drive.getDriverCarNumber())
                                        .driveCost(drive.getDriveCost())
                                        .driveDistance(drive.getDriveDistance())
                                        .build(),
                                Collectors.toList())
                ));

        log.info("List 형식으로 key, value 리스트 변환 후 반환");
        return groupedByDate.entrySet().stream()
                .map(entry -> GetDto.HistoryResponse.builder()
                        .driveDate(entry.getKey())
                        .historyData(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }
}
