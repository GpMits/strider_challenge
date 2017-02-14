package gustavo.controllers;

import gustavo.models.TaskStatus;
import gustavo.models.Tasks;
import gustavo.models.User;
import gustavo.daos.TasksDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/task")
public class TaskController {

  @Autowired
  private TasksDao _taskDao;
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(int id) {
    try {
      Tasks task = new Tasks(id);
      _taskDao.delete(task);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully deleted!";
  }
  
  @RequestMapping(value="/get-by-id")
  @ResponseBody
  public Tasks getById(int id) {
    Tasks task = null;
    try {
    	task = _taskDao.getById(id);
    }
    catch(Exception ex) {
      return null;
    }
    return task;
  }

  @RequestMapping(value="/save")
  @ResponseBody
  public ResponseEntity<Void> create(@RequestBody Tasks tsk) {
	System.out.println(tsk.toString());
	HttpHeaders headers = new HttpHeaders();
    try {
      Tasks task = new Tasks(tsk.getDescription(), new User(1), new TaskStatus(1));
      _taskDao.save(task);
    }
    catch(Exception ex) {
    	return new ResponseEntity<Void>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }
  
  @RequestMapping(value="/list")
  @ResponseBody
  public List<Tasks> listByUser() {
	  List<Tasks> allTasks = null;
	  try {
		  allTasks = _taskDao.listByUser(new User(1));
	  }
	  catch(Exception ex) {
		  return null;
	  }
	  return allTasks;
  }
} // class UserController
