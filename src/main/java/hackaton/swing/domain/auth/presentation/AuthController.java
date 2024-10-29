package hackaton.swing.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = "application/json; charset=UTF8")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
}
