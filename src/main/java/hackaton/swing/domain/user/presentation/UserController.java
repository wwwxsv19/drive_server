package hackaton.swing.domain.user.presentation;

import hackaton.swing.domain.drive.service.DriveService;
import hackaton.swing.domain.user.presentation.dto.GetDto;
import hackaton.swing.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final DriveService driveService;

    @GetMapping("/history")
    public ResponseEntity<?> getDriveHistory() {
        log.info("유저 이용 기록 불러오기 시작");

        List<GetDto.HistoryResponse> response = driveService.getDriveHis();

        log.info("유저 이용 기록 불러오기 완료");
        return ResponseEntity.ok().body(response);
    }
}
