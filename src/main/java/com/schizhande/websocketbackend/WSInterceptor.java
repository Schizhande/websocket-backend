package com.schizhande.websocketbackend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@RequiredArgsConstructor
public class WSInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        log.debug("### Web Socket Interceptor");


        val headers = message.getHeaders();
        val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        log.info("### headers {}", headers);
        String requestTokenHeader = "";

        val messageType = (SimpMessageType) headers.get(StompHeaderAccessor.MESSAGE_TYPE_HEADER);

        log.debug("### message type {}", messageType);

        if (messageType == SimpMessageType.CONNECT) {

            accessor.setUser(new WSPrincipal("admin"));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    null, null, null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        }

        return message;
    }
}

