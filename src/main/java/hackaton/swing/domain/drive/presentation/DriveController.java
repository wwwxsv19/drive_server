package hackaton.swing.domain.drive.presentation;

import hackaton.swing.domain.drive.service.DriveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/drive", produces = "application/json; charset=UFT8")
@RequiredArgsConstructor
@Slf4j
public class DriveController {
    private final DriveService driveService;
}
