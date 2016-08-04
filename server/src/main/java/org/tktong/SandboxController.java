package org.tktong;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SandboxController {
	@Autowired
	private SandboxService sandboxService;

	@RequestMapping(value = "/abcd*")
	public String sandbox(Model model) {
		return "index";
	}

	@RequestMapping(value = "/admin/*")
	public String sandbox2(Model model) {
		return "index";
	}
}