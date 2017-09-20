package com.revature.smp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class GreetingsController.
 */
@Controller
public class GreetingsController {
	/**
	 * Greeting endpoint, returns greeting.html
	 *
	 * @param name
	 *            the name
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping("/greetings")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";

	}
}


