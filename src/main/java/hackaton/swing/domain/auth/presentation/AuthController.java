package hackaton.swing.domain.auth.presentation;

import hackaton.swing.domain.drive.DriveHis;
import hackaton.swing.domain.drive.repository.DriveHisRepository;
import hackaton.swing.domain.user.driver.repository.Driver;
import hackaton.swing.domain.user.driver.repository.repository.DriverRepository;
import hackaton.swing.domain.user.passenger.Passenger;
import hackaton.swing.domain.user.passenger.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final DriveHisRepository driveHisRepository;

    @PostMapping("/start")
    public ResponseEntity<?> init() {
        if (passengerRepository.existsById("test")) {
            return ResponseEntity.ok().body("이미 DB 정보가 존재함!");
        }

        Map<String, String> keywords = new HashMap<>();
        keywords.put("테스트", "테스트");
        keywords.put("비흡연", "비흡연");

        passengerRepository.save(
                Passenger.builder()
                    .passengerId("test")
                    .passengerName("박강은")
                    .passengerKeywords(keywords)
                    .passengerPassword("1234")
                    .build()
        );

        driverRepository.save(
                Driver.builder()
                    .driverId("ttest")
                    .driverName("소마고")
                    .driverCarNumber("15바 4846")
                    .driverKeywords(keywords)
                    .driverPassword("1234")
                    .build()
        );

        driveHisRepository.save(
                DriveHis.builder()
                        .createdTime("2024-10-30")
                        .passengerId("test")
                        .driverId("ttest")
                        .driverCarNumber("15바 4046")
                        .driveCost(20000)
                        .driveDistance(20)
                        .build()
        );

        driveHisRepository.save(
                DriveHis.builder()
                        .createdTime("2024-10-29")
                        .passengerId("test")
                        .driverId("ttest")
                        .driverCarNumber("15바 4046")
                        .driveCost(18000)
                        .driveDistance(18)
                        .build()
        );

        driveHisRepository.save(
                DriveHis.builder()
                        .createdTime("2024-09-18")
                        .passengerId("test")
                        .driverId("ttest")
                        .driverCarNumber("15바 4046")
                        .driveCost(9000)
                        .driveDistance(5)
                        .build()
        );

        return ResponseEntity.ok().body("서버 init 성공!");
    }
}
