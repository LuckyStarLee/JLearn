import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-28 18:29
 **/
@SpringBootApplication
@ComponentScan("com.lee.rabbitmq")
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
