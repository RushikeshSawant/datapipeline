package demo.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import demo.app.model.User;

/**
 * Service to publish data on broker topic
 * @author rushikesh
 *
 */
@Service
public class UserDataFeedService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDataFeedService.class);
	private final SimpMessageSendingOperations template;

	@Autowired
	public UserDataFeedService(SimpMessageSendingOperations template) {
		this.template = template;
	}

	public void publishData(User user) {
		LOG.info("[PUBLISHED_TO_USERFEED]: " + user.getEmail());
		template.convertAndSend("/topic/userfeed", user);
	}
}
