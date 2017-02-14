package gustavo.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gustavo.models.TaskStatus;

@Repository
@Transactional
public class TaskStatusDao {
  
  @Autowired
  private SessionFactory _sessionFactory;
  
  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public void save(TaskStatus tStatus) {
    getSession().save(tStatus);
    return;
  }
  
  public void delete(TaskStatus tStatus) {
    getSession().delete(tStatus);
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<TaskStatus> getAll() {
    return getSession().createQuery("from TaskStatus").list();
  }
  
  public TaskStatus getByName(String name) {
    return (TaskStatus) getSession().createQuery(
        "from TaskStatus where name = :name")
        .setParameter("name", name)
        .uniqueResult();
  }

  public TaskStatus getById(long id) {
    return (TaskStatus) getSession().load(TaskStatus.class, id);
  }

  public void update(TaskStatus tStatus) {
    getSession().update(tStatus);
    return;
  }

} // class TaskStatusDao
