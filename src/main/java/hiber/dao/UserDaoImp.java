package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      try {
         return query.getSingleResult().getUser();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   @Transactional
   public void clearTables() {
      sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();
      sessionFactory.getCurrentSession().createQuery("DELETE FROM Car").executeUpdate();
   }
}
