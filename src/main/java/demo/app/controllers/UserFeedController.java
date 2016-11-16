package demo.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller to serve sample test page for data pipeline
 * 
 * @author rushikesh
 *
 */
@Controller
@RequestMapping("/")
public class UserFeedController {

	@RequestMapping(path = "userfeed", method = RequestMethod.GET)
	public String getFeedPage() {
		return "users";
	}
}
