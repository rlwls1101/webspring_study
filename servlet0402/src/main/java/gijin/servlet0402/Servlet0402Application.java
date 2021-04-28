package gijin.servlet0402;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class Servlet0402Application {

	public static void main(String[] args) {
		SpringApplication.run(Servlet0402Application.class, args);
	}

}
