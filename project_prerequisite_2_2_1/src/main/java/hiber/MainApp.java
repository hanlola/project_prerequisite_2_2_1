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

      User lola = new User("Lola","Han","lola@mail.ru");
      User vlad = new User("Vlad","Tsoy","vlad@mail.ru");
      User tema = new User("Tema","Tsoy","tema@mail.ru");
      User artur = new User("Artur","Han","artur@mail.ru");

      Car lola_car = new Car("audi",132);
      Car vlad_car = new Car("honda",8765);
      Car tema_car = new Car("mers",4567);
      Car artur_car = new Car("bmw",65);

      userService.add(lola.setCar(lola_car).setUser(lola));
      userService.add(vlad.setCar(vlad_car).setUser(vlad));
      userService.add(tema.setCar(tema_car).setUser(tema));
      userService.add(artur.setCar(artur_car).setUser(artur));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
//         System.out.println(user + " " + user.getCar());
      }
      System.out.println(userService.getUserByCar("mers",4567));

      context.close();
   }
}
