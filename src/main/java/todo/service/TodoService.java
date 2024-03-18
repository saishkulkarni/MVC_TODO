package todo.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import todo.dao.TodoDao;
import todo.dto.Customer;
import todo.dto.Task;

@Component
public class TodoService {

	@Autowired
	TodoDao dao;

	public String saveUser(ModelMap map, Customer customer) {
		List<Customer> list = dao.findByEmail(customer.getEmail());
		if (list.isEmpty()) {
			dao.saveCustomer(customer);
			map.put("success", "Account Created Success");
			return "login.jsp";
		} else {
			map.put("failure", "Account Already Exists");
			return "signup.jsp";
		}
	}

	public String login(ModelMap map, String email, String password, HttpSession session) {
		List<Customer> list = dao.findByEmail(email);

		if (list.isEmpty()) {
			map.put("failureEmail", "Invalid Email");
			return "login.jsp";
		} else {
			Customer customer = list.get(0);
			System.out.println(customer.getPassword());
			if (password.equals(customer.getPassword())) {
				session.setAttribute("customer", customer);

				map.put("success", "Login Success");

				List<Task> tasks = dao.fetchTasks(customer.getId());
				map.put("tasks", tasks);

				return "home.jsp";
			} else {
				map.put("failurePassword", "Invalid Password");
				return "login.jsp";
			}
		}
	}

	public String addTask(ModelMap map, HttpSession session, Task task) {

		task.setCreatedTime(LocalDateTime.now());

		Customer customer = (Customer) session.getAttribute("customer");

		task.setCustomer(customer);

		dao.saveTask(task);

		map.put("success", "Task Added Success");

		List<Task> tasks = dao.fetchTasks(customer.getId());
		map.put("tasks", tasks);

		return "home.jsp";
	}

	public String complete(ModelMap map, HttpSession session, int id) {

		Task task = dao.findById(id);
		task.setStatus(true);
		dao.updateTask(task);

		map.put("success", "Status Changed Success");

		Customer customer = (Customer) session.getAttribute("customer");
		List<Task> tasks = dao.fetchTasks(customer.getId());
		map.put("tasks", tasks);

		return "home.jsp";
	}

	public String delete(ModelMap map, HttpSession session, int id) {
		Task task = dao.findById(id);

		dao.deleteTask(task);

		map.put("success", "Task Deleted Success");

		Customer customer = (Customer) session.getAttribute("customer");
		List<Task> tasks = dao.fetchTasks(customer.getId());
		map.put("tasks", tasks);

		return "home.jsp";
	}

	public String updateTask(ModelMap map, HttpSession session, Task task, int id) {

		Customer customer = (Customer) session.getAttribute("customer");

		task.setId(id);
		task.setCreatedTime(LocalDateTime.now());
		task.setStatus(false);
		task.setCustomer(customer);

		dao.updateTask(task);

		List<Task> tasks = dao.fetchTasks(customer.getId());
		map.put("tasks", tasks);
		map.put("success", "Task Updated Success");
		return "home.jsp";
	}

	public String logout(HttpSession session, ModelMap map) {
		session.removeAttribute("customer");
		map.put("success", "Logout Success");
		return "home.jsp";
	}

	public String editTask(int id, ModelMap map) {
		Task task = dao.findById(id);
		map.put("task", task);
		return "edittask.jsp";
	}
}