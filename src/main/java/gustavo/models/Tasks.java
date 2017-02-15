package gustavo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Tasks {

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String description;

	@NotNull 
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "task_status_id")
	private TaskStatus taskStatus;
	
	private String img;
	
	public Tasks (){
		this.id = 0;
		this.description = "none";
		this.user = null;
		this.taskStatus = null;
	}
	
	public Tasks(int id) { 
	    this.id = id;
	  }
	
	public Tasks(String description) {
	    this.description = description;
	}
	
	public Tasks(String description, User usr, TaskStatus tStatus) {
	    this.description = description;
	    this.user = usr;
	    this.taskStatus = tStatus;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User usr){
		this.user = usr;
	}

	public TaskStatus getTaskStatus (){
		return this.taskStatus;
	}

	public void setTaskStatus (TaskStatus taskStatus){
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString(){
		return "Task\n id="+id+", name="+description;
	}
}