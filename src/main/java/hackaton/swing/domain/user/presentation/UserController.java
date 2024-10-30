package hackaton.swing.domain.user.presentation;

import hackaton.swing.domain.drive.service.DriveService;
import hackaton.swing.domain.user.presentation.dto.GetDto;
import hackaton.swing.domain.user.presentation.dto.UpdateDto;
import hackaton.swing.domain.user.service.DriverService;
import hackaton.swing.domain.user.service.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final PassengerService passengerService;
    private final DriverService driverService;
    private final DriveService driveService;

    // Passenger
    @GetMapping("/history")
    public ResponseEntity<?> getDriveHistory() {
        log.info("승객 이용 기록 불러오기 시작");

        List<GetDto.HistoryResponse> response = driveService.getDriveHis();

        log.info("승객 이용 기록 불러오기 완료");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/p/keyword/{id}")
    public ResponseEntity<?> getKeywordsP(@PathVariable String id) {
        log.info("승객 선호 키워드 불러오기 시작");

        try {
            GetDto.KeywordsResponse response = passengerService.getKeywordsP(id);

            log.info("승객 선호 키워드 불러오기 완료");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/p/keyword")
    public ResponseEntity<?> updateKeywordsP(@RequestBody UpdateDto.KeywordsRequest request) {
        log.info("승객 선호 키워드 수정 시작");

        try {
            UpdateDto.Response response = passengerService.updateKeywordsP(request);

            log.info("승객 선호 키워드 수정 완료");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Driver
    @GetMapping("/d/keyword/{id}")
    public ResponseEntity<?> getKeywordsD(@PathVariable String id) {
        log.info("기사 선호 키워드 불러오기 시작");

        try {
            GetDto.KeywordsResponse response = driverService.getKeywordsD(id);

            log.info("기사 선호 키워드 불러오기 완료");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/d/keyword")
    public ResponseEntity<?> updateKeywordsD(@RequestBody UpdateDto.KeywordsRequest request) {
        log.info("기사 선호 키워드 수정 시작");

        try {
            UpdateDto.Response response = driverService.updateKeywordsD(request);

            log.info("기사 선호 키워드 수정 완료");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
