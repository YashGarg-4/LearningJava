
// package com.komdox.chipsTrial.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.*;

// @Configuration
// @EnableWebSocketMessageBroker // Turns on the messaging server
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         // The "front door" for all WebSocket connections.
//         registry.addEndpoint("/pokerWS").withSockJS();
//     }

//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         // Mailboxes for server-to-client broadcasts (public announcements).
//         registry.enableSimpleBroker("/topic");
        
//         // Prefix for client-to-server messages that need processing by a controller.
//         registry.setApplicationDestinationPrefixes("/app");
//     }
// }
