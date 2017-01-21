package fr.univrouen.iataaaserver.config;

import fr.univrouen.iataaaserver.controller.SynchronizeHandlerIA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfigIA implements WebSocketConfigurer {

    @Autowired
    private SynchronizeHandlerIA synchronizeHandlerIA;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(synchronizeHandlerIA, "/connect")
                .setAllowedOrigins("*");
    }
}