package fr.univrouen.iataaaserver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univrouen.iataaaserver.dto.WebSocketGameBean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class SynchronizeHandlerIA extends TextWebSocketHandler {

    private WebSocketSession session;
    private final ObjectMapper mapper= new ObjectMapper();

    public void gameChangement(WebSocketGameBean message) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
    
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
        }
    }

}
