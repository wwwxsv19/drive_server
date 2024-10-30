package hackaton.swing.domain.match.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackaton.swing.domain.match.MatchRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchService {
    private final ObjectMapper objectMapper;
    private Map<String, MatchRoom> matchRooms;

    @PostConstruct
    private void init() {
        matchRooms = new LinkedHashMap<>();
    }

    public List<MatchRoom> findAllRoom() {
        return new ArrayList<>(matchRooms.values());
    }

    public MatchRoom findRoomById(String matchRoomId) {
        return matchRooms.get(matchRoomId);
    }

    public MatchRoom createRoom() {
        String randomId = UUID.randomUUID().toString();
        MatchRoom matchRoom = MatchRoom.builder()
                .matchRoomId(randomId)
                .build();
        matchRooms.put(randomId, matchRoom);
        return matchRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
