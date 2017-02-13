package gustavo.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gustavo.models.User;

@Repository
@Transactional
public class UserDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(User user) {
    getSession().save(user);
    return;
  }
  
  public void delete(User user) {
    getSession().delete(user);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getSession().createQuery("from User").list();
  }
  
  public User getByUsername(String username) {
    return (User) getSession().createQuery(
        "from User where username = :username")
        .setParameter("username", username)
        .uniqueResult();
  }

  public User getById(long id) {
    return (User) getSession().load(User.class, id);
  }

  public void update(User user) {
    getSession().update(user);
    return;
  }

} // class UserDao
