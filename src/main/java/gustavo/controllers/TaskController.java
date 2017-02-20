package gustavo.controllers;

import gustavo.models.TaskStatus;
import gustavo.models.Tasks;
import gustavo.models.User;
import gustavo.daos.TasksDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    System.out.println(id);
    try {
    	task = _taskDao.getById(id);
    }
    catch(Exception ex) {
      return new Tasks(ex.getMessage(), null, null);
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
  
  @RequestMapping(value="/list-pending")
  @ResponseBody
  public List<Tasks> listPending() {
	  List<Tasks> allTasks = null;
	  try {
		  allTasks = _taskDao.listPending(new User(1));
	  }
	  catch(Exception ex) {
		  return null;
	  }
	  return allTasks;
  }
  
  @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<byte[]> uploadFile(@RequestParam("file") MultipartFile file) {
	try {
      byte[] bytes = file.getBytes();
      String directory = new File("C:\\Users\\Gustavo\\Desktop\\strider_challenge\\src\\main\\webapp\\static\\image\\").getAbsolutePath();
      String fileName = file.getOriginalFilename();
      String filePath = Paths.get(directory, fileName).toString();
      
      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
      stream.write(bytes);
      stream.close();
      
      Tasks task = _taskDao.getById(Integer.parseInt(file.getOriginalFilename().replaceAll(".png", "")));
      task.setTaskStatus(new TaskStatus(2));
      task.setImg(file.getOriginalFilename());
      _taskDao.update(task);
    }
    catch(Exception ex) {
    	ex.printStackTrace();
    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  
} // class UserController
