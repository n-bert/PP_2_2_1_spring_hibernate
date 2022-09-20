package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public User getUserByCar(String model, int series) {
      return userDao.getUserByCar(model, series);
   }

   @Override
   public void clearTables() {
   userDao.clearTables();
   }

}
