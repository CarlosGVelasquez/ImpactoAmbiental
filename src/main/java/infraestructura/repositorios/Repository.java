package infraestructura.repositorios;

import infraestructura.db.HibernateUtils;
import org.hibernate.Session;
import java.util.List;

public abstract class Repository<T> {
  private Class<T> type;

  public Repository(Class<T> type) {
    this.type = type;
  }

  public void agregar(Object unObjeto) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(unObjeto);
    session.getTransaction().commit();
    session.close();
  }

  public void modificar(Object unObjeto) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    session.beginTransaction();
    session.saveOrUpdate(unObjeto);
    session.getTransaction().commit();
    session.close();
  }

  public void eliminar(Object unObjeto) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    session.beginTransaction();
    session.delete(unObjeto);
    session.getTransaction().commit();
    session.close();
  }

  public List<T> buscarTodos() {
    Session session = HibernateUtils.getSessionFactory().openSession();
    session.beginTransaction();
    List<T> entities = session.createQuery("from " + type.getName()).list();
    session.getTransaction().commit();
    session.close();
    return entities;
  }

  public T buscar(Integer id) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    session.beginTransaction();
    T entity = (T) session.get(type, id);
    session.getTransaction().commit();
    session.close();
    return entity;
  }

}
