package hackaton.swing.domain.match.presentation;

import hackaton.swing.domain.match.MatchRoom;
import hackaton.swing.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
@Slf4j
public class MatchController {
    private final MatchService matchService;

    @PostMapping("/")
    public MatchRoom createRoom() {
        return matchService.createRoom();
    }

    @GetMapping("/")
    public List<MatchRoom> findAllRoom() {
        return matchService.findAllRoom();
    }
}
