package demo.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Websocket/STOMP related configuration
 * @author rushikesh
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Value("${spring.messagebroker.user}")
    private String username;
    
    @Value("${spring.messagebroker.password}")
    private String password;
    
    @Value("${spring.messagebroker.relayhost}")
    private String relayHost;
    
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/livefeed").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableStompBrokerRelay("/queue", "/topic")
		.setClientLogin(username)
		.setClientPasscode(password)
		.setRelayHost(relayHost);
	}
}
