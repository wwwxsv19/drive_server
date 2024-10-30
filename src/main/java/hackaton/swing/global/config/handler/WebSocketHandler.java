package hackaton.swing.global.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackaton.swing.domain.match.MatchRoom;
import hackaton.swing.domain.match.presentation.dto.MatchMessage;
import hackaton.swing.domain.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final MatchService matchService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);

        MatchMessage matchMessage = objectMapper.readValue(payload, MatchMessage.class);

        MatchRoom matchRoom = matchService.findRoomById(matchMessage.getMatchRoomId());

        matchRoom.handlerActions(session, matchMessage, matchService);
    }
}
