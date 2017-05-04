package rpg.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FullName {
	@Column (name = "first_name")
	private String firstName;
	@Column (name = "second_name")
	private String secondName;
	
	public FullName() {}
	public FullName(String first, String second) {
		this.firstName = first;
		this.secondName = second;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
}
