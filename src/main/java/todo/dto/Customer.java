package todo.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import todo.helper.AES;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String email;
	String password;
	LocalDate dob;
	long mobile;
	String gender;
	
	public void setPassword(String password) {
		this.password=AES.encrypt(password, "123");
	}
	
	public String getPassword() {
		return AES.decrypt(password, "123");
	}

	public void setDob(String dob) {
		this.dob = LocalDate.parse(dob);
	}
}
