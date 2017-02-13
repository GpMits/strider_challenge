package gustavo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="taks_status")
public class TaskStatus {

	@Id
	@Column(name = "task_status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String name;

	public TaskStatus (){
		this.id = 0;
		this.name = "none";
	}
	
	public TaskStatus(int id) { 
	    this.id = id;
	  }
	
	public TaskStatus(String name) {
	    this.name = name;
	}
	  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString(){
		return "TaskStatus \n id="+id+", name="+name;
	}
}