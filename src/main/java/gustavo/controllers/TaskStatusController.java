package gustavo.controllers;

import gustavo.models.TaskStatus;
import gustavo.daos.TaskStatusDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/taskStatus")
public class TaskStatusController {

  @Autowired
  private TaskStatusDao _tStatusDao;
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(int id) {
    try {
      TaskStatus tStatus = new TaskStatus(id);
      _tStatusDao.delete(tStatus);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "TaskStatus succesfully deleted!";
  }
  
  @RequestMapping(value="/get-by-name")
  @ResponseBody
  public String getByTaskStatusname(String name) {
    String tStatusId;
    try {
      TaskStatus tStatus = _tStatusDao.getByName(name);
      tStatusId = String.valueOf(tStatus.getId());
    }
    catch(Exception ex) {
      return "Task status not found" + ex.getMessage();
    }
    return "The task status id is: " + tStatusId;
  }

  @RequestMapping(value="/save")
  @ResponseBody
  public String create(String email, String name) {
    try {
      TaskStatus tStatus = new TaskStatus(name);
      _tStatusDao.save(tStatus);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "TaskStatus succesfully saved!";
  }

} // class TaskStatusController
