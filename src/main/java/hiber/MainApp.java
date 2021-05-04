package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("dima","smith","d@sd",new Car("tesla",5)));
      userService.add(new User("vasya","smith","d@sd",new Car("bmw",3)));
      userService.add(new User("petr","smith","d@sd",new Car("bmw",6)));
      userService.add(new User("hank","smith","d@sd",new Car("toyota",10)));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUserByCar("tesla",5).getFirstName());
      System.out.println(userService.getUserByCar("toyota",10).getFirstName());

      userService.add(new User("lucky","smith","d@sd",new Car("mercedes",666)));

       System.out.println(userService.getUserByCar("mercedes",666).getFirstName());
      context.close();
   }
}
