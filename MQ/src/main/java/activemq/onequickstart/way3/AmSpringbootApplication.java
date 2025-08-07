package activemq.onequickstart.way3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmSpringbootApplication {

    public static void main(String[] args) {
        System.out.println("启动");
        SpringApplication.run(AmSpringbootApplication.class, args);
    }
}
