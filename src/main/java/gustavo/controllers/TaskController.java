package gustavo.controllers;

import gustavo.models.TaskStatus;
import gustavo.models.Tasks;
import gustavo.models.User;
import gustavo.daos.TasksDao;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/task")
public class TaskController {

  @Autowired
  private TasksDao _taskDao;
  
  @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
	HttpHeaders headers = new HttpHeaders();
    try {
      Tasks task = new Tasks(id);
      _taskDao.delete(task);
    }
    catch(Exception ex) {
    	return new ResponseEntity<Void>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
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
