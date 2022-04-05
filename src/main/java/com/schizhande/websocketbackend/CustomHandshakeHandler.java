package com.schizhande.websocketbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        log.info("headers {}", request.getPrincipal());
        log.info("attributes {}", attributes);
        // generate user name by UUID
        return new WSPrincipal(UUID.randomUUID().toString());
    }
}
