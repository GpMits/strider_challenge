package gustavo.controllers;

import gustavo.models.User;
import gustavo.daos.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {

  @Autowired
  private UserDao _userDao;
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(int id) {
    try {
      User user = new User(id);
      _userDao.delete(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully deleted!";
  }
  
  @RequestMapping(value="/get-by-username")
  @ResponseBody
  public String getByUsername(String username) {
    String userId;
    try {
      User user = _userDao.getByUsername(username);
      userId = String.valueOf(user.getId());
    }
    catch(Exception ex) {
      return "User not found" + ex.getMessage();
    }
    return "The user id is: " + userId;
  }

  @RequestMapping(value="/save")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User(email, name);
      _userDao.save(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }

} // class UserController
