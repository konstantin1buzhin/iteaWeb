package web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("request")
public class ControllerRegister {

	@Autowired
	private User user;

	@Autowired
	private MySQLUserDAO userDB;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getGetRegisterPage(HttpSession session) {
		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		return "register";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST, params = { "login", "name", "password", "age",
			"gender", "comments", "address", "amigo" })
	public String getPostRegisterPage(@RequestParam("login") String login, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("age") String age,
			@RequestParam("gender") String gender, @RequestParam("comments") String comments,
			@RequestParam("address") String address, @RequestParam("amigo") String amigo, HttpSession session, ModelMap model) {
		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setAge(Integer.valueOf(age));
		user.setGender(gender);
		user.setComments(comments);
		user.setAddress(address);
		user.setAmigo(amigo);
		userDB.setUser(user);
		model.addAttribute("checkLogin", userDB.getCheckLogin());
		if(!userDB.getCheckLogin()) {
		model.addAttribute("registration", userDB.getRegistration());
		} else {
			model.addAttribute("registration", "fault");
		}
		
		
		return "register";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String getPostRegisterPage(HttpSession session) {
		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		return "register";
	}
}
