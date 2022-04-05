package com.schizhande.websocketbackend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class WSInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

            if (raw instanceof Map) {
                Object name = ((Map) raw).get("token");
                log.info("Token {}", name);

                if (name instanceof ArrayList) {
                    accessor.setUser(new WSPrincipal(((ArrayList<String>) name).get(0).toString()));
                }
            }
        }
//        log.debug("### Web Socket Interceptor");
//
//
//        val headers = message.getHeaders();
//        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
//        log.info("### headers {}", headers);
//        String requestTokenHeader = "";
//
//        val messageType = (SimpMessageType) headers.get(StompHeaderAccessor.MESSAGE_TYPE_HEADER);
//
//        log.debug("### message type {}", messageType);
//
//        if (messageType == SimpMessageType.CONNECT) {
//
//            accessor.setUser(new WSPrincipal("admin"));
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                    null, null, null);
//
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//
//        }

        return message;
    }
}

