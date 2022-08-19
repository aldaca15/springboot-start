package com.test.springboot;

import com.test.springboot.bean.MyBean;
import com.test.springboot.component.MyComponent;
import com.test.springboot.entity.User;
import com.test.springboot.pojo.properties.UserProperties;
import com.test.springboot.repository.UserRepository;
import com.test.springboot.services.AgeCalculation;
import com.test.springboot.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@EnableTransactionManagement
public class Application implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(this.getClass());

    private MyBean myBean;
    private MyComponent myComponent;
    private UserProperties userProperties;

    private UserRepository userRepository;

    private UserService userService;

    public Application(MyBean myBean, @Qualifier("cualquierNombre") MyComponent myComponent, UserProperties userProperties, UserRepository userRepository, UserService userService) {
        this.myBean = myBean;
        this.myComponent = myComponent;
        this.userProperties = userProperties;

        // Adds repository
        this.userRepository = userRepository;

        // Adds service
        this.userService = userService;
    }

    @Bean
    public Function<String, String> uppercase() {
        return (String value) -> value.toUpperCase();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        //initialRunTest(); // Tests configuration
        //saveUsersInDataBase(); // Not again

        saveUsersTransactional(); // Test transaction1
        saveUsersTransactional2(); // Test transaction2

        // Return user
        getInfoJPQLfromUser();

        // To check actuator visit: {context}/actuator/health

    }

    /**
     * Store users in database
     */
    private void saveUsersInDataBase() {
        User user1 = new User("Ali","ali.adamecantoran@hcl.com", LocalDate.of(1991,12,15));
        User user2 = new User("Diaz, Julio","julio.diaz@hcl.com", LocalDate.of(1982,7,13));
        User user3 = new User("Kenya","kenya@gmail.com", LocalDate.of(1996,1,23));
        User user4 = new User("Max","max.adame@gmail.com", LocalDate.of(1969,1,15));
        User user5 = new User("Diaz, Bruno","imbatman@ratalada.com", LocalDate.of(1982,7,13));

        List<User> listUsers = Arrays.asList(user1,user2,user3,user4,user5);
        listUsers.stream().forEach(userRepository::save);
        // Another way
        //userRepository.saveAll(listUsers);
    }

    private void saveUsersTransactional() {
        User user0 = new User("Miguel","miguel.adamecantoran@hcl.com", LocalDate.of(1991,12,15));

        List<User> listWithUsers = Arrays.asList(user0);
        try {
            userService.saveTransactional(listWithUsers);
        } catch (Exception e) {
            logger.error("Exception, due to transactional method 1");
        }

    }

    private void saveUsersTransactional2() {

        User user1 = new User("Miguel","miguel.adamecantoran@hcl.com", LocalDate.of(1991,12,15));
        User user2 = new User("Miguel","miguel.adamecantoran@hcl.com", LocalDate.of(1991,12,15));

        List<User> listUsers = Arrays.asList(user1,user2);
        try {
            userService.saveTransactional(listUsers);
        } catch (Exception e) {
            logger.error("Exception, due to transactional method 2");
        }

    }

    private void saveUsersTransactional3() { // It's suppose to not able to save, as user email exists
        try {
            Long id = 1L;
            //Integer id = 1;
            User testUser = userService.getById(id);
            if(testUser != null) {
                testUser.setEmail("miguel.adamecantoran@hcl.com");
                userService.update(testUser, id.longValue());
            }
        } catch (Exception e) {
            logger.error("Exception, on update");
        }

    }

    private void getInfoJPQLfromUser() {
        logger.info("User with method getInfoJPQLfromUser "+
                userRepository.findByUserEmail("ali.adamecantoran@hcl.com")
                        .orElseThrow(() -> new RuntimeException("User not found")));
        userRepository.findAndSort("Diaz", Sort.by("id").descending())
                .stream()
                .forEach(user -> logger.info("getInfoJPQLfromUser" + user.getEmail()));
        /* Another way as
        for (User u: found) {
            logger.info(u.getEmail());
        }*/
        /* Other one
        userRepository.findAndSort("user", Sort.by("id").descending())
				.forEach(LOGGER::info);
         */

        userRepository.findByName("Diaz, Julio").stream().forEach(user -> logger.info("Usuario con Query Method " + user));

        logger.info("Usuario con query method userRepository.findByEmailAndName() " + userRepository.findByEmailAndName("julio.diaz@hcl.com", "Diaz, Julio")
                .orElseThrow(() -> new RuntimeException("User not found")));

        userRepository.findByNameLike("%Diaz%").stream().forEach(user -> logger.info("Usuario findByNameLike " + user));
        userRepository.findByNameLike("%Diaz%").stream().forEach(user -> logger.info("Usuario findByNameLike " + user));

        userRepository.findByNameOrEmail("Kenya G.", "ali.adamecantoran@hcl.com").stream().forEach(user -> logger.info("Usuario findByNameOrEmail " + user + " with age " + AgeCalculation.getAge(user.getBirthDate()))); // fetch 2 users

        userRepository.findByBirthDateBetween(LocalDate.of(1982,7,13), LocalDate.of(1996,10,14))
                .stream().forEach(user -> logger.info("Usuario findByBirthDateBetween " + user)); // Inclusive in dates

        //userRepository.findByNameLikeOrderByIdDesc("%Diaz%").stream().forEach(user -> logger.info("Usuario findByNameLikeOrderByIdDesc " + user)); //Same below
        userRepository.findByNameContainingOrderByIdDesc("Diaz").stream().forEach(user -> logger.info("Usuario findByNameLikeOrderByIdDesc " + user));

        logger.info("Usuario con query method userRepository.getAllByBirthDateAndEmail() "
                + userRepository.getAllByBirthDateAndEmail(LocalDate.of(1991,12,15), "ali.adamecantoran@hcl.com")
                .orElseThrow(() -> new RuntimeException("No namedParameter results")));

        // Example reference operator Java 8
        List<User> usersMatch = userRepository.findAndSort("Diaz", Sort.by("id").descending());
        usersMatch.forEach(System.out::println); // Fist way print reference by double colon
        // Way #2
        Consumer<User> testEmail = user -> System.out.println("User email valid: " + user.isUserEmailValid());
        usersMatch.forEach(testEmail);
        // Way #3
        usersMatch.forEach(e -> System.out.println(e.isUserEmailValid()));

    }

    /**
     * Method that loads injected beans for test of configuration
     */
    private void initialRunTest() {
        logger.debug("inicializando app");
        System.out.println(myBean.hello());
        myComponent.printSomething();
        System.out.println(userProperties.toString());
        System.out.println("hola mundo utilizando spring boot devtools");
        logger.info("error en el aplicativo");
        Function function = uppercase();
        System.out.println(function.apply(userProperties.getEmail()));
        try {
            int result = 1/0;
        } catch (ArithmeticException e) {
            logger.error("Esto es un error");
        }
    }
}
