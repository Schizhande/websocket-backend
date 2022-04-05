package com.schizhande.websocketbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

//@Configuration
public class WSSecurity extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpSubscribeDestMatchers("/user/greeting").permitAll();

    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }


}
