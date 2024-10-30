package hackaton.swing.domain.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hackaton.swing.domain.match.presentation.dto.MatchMessage;
import hackaton.swing.domain.match.service.MatchService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchRoom {
    private String matchRoomId;

    @JsonIgnore
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public MatchRoom(String matchRoomId) {
        this.matchRoomId = matchRoomId;
    }

    public void handlerActions(WebSocketSession session, MatchMessage matchMessage, MatchService matchService) {
        sessions.add(session);
        sendMessage(matchMessage, matchService);
    }

    private <T> void sendMessage(T message, MatchService matchService) {
        sessions.parallelStream()
                .forEach(session -> matchService.sendMessage(session, message));
    }
}
