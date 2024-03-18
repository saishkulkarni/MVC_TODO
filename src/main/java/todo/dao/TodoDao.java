package todo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import todo.dto.Customer;
import todo.dto.Task;

@Component
public class TodoDao {

	@Autowired
	EntityManager manager;

	public void saveCustomer(Customer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}

	public List<Customer> findByEmail(String email) {
		return manager.createQuery("select x from Customer x where email=?1").setParameter(1, email)
				.getResultList();
	}

	public void saveTask(Task task) {
		manager.getTransaction().begin();
		manager.persist(task);
		manager.getTransaction().commit();
	}

	public List<Task> fetchTasks(int id) {
		return manager.createQuery("select x from Task x where customer_id=?1").setParameter(1, id).getResultList();
	}

	public Task findById(int id) {
		return manager.find(Task.class, id);
	}

	public void updateTask(Task task) {
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
	}

	public void deleteTask(Task task) {
		manager.getTransaction().begin();
		manager.remove(task);
		manager.getTransaction().commit();
	}
}