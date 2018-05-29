import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-29 10:31
 **/
@SpringBootApplication
@ComponentScan("com.lee.rabbitmq")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
