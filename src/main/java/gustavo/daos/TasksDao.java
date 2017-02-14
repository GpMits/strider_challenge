package gustavo.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gustavo.models.Tasks;
import gustavo.models.User;

@Repository
@Transactional
public class TasksDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(Tasks task) {
    getSession().save(task);
    return;
  }
  
  public void delete(Tasks task) {
    getSession().delete(task);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<Tasks> getAll() {
    return getSession().createQuery("from Tasks").list();
  }
  
  @SuppressWarnings("unchecked")
  public List<Tasks> listByUser(User usr) {
    return getSession().createQuery("from Tasks where user_id = :idUser")
    		.setParameter("idUser", usr.getId()).list();
  }
  
  public Tasks getById(long id) {
    return (Tasks) getSession().load(Tasks.class, id);
  }

  public void update(Tasks task) {
    getSession().update(task);
    return;
  }

} // class TasksDao
