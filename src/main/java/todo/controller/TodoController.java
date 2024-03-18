package todo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import todo.dto.Customer;
import todo.dto.Task;
import todo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService service;

	@GetMapping({ "/", "/login" })
	public String loadLogin() {
		return "login.jsp";
	}

	@GetMapping("/signup")
	public String loadSignup() {
		return "signup.jsp";
	}

	@PostMapping("/signup")
	public String signup(Customer customer, ModelMap map) {
		return service.saveUser(map, customer);
	}

	@PostMapping("/add-task")
	public String addTask(ModelMap map, HttpSession session, Task task) {
		return service.addTask(map, session, task);
	}

	@GetMapping("/complete")
	public String complete(ModelMap map, HttpSession session, @RequestParam int id) {
		return service.complete(map, session, id);
	}

	@GetMapping("/delete")
	public String delete(ModelMap map, HttpSession session, @RequestParam int id) {
		return service.delete(map, session, id);
	}

	@PostMapping("/login")
	public String login(ModelMap map, HttpSession session, @RequestParam String email, @RequestParam String password) {
		return service.login(map, email, password, session);
	}

	@PostMapping("/update-task")
	public String updateTask(ModelMap map, HttpSession session, @RequestParam int id, Task task) {
		return service.updateTask(map, session, task, id);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {
		return service.logout(session, map);
	}

	@GetMapping("/edit-task")
	public String editTask(@RequestParam int id, ModelMap map) {
		return service.editTask(id, map);
	}

}
