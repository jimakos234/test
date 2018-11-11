package tt.test.testId.testApp;

import org.apache.cxf.common.i18n.Exception;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class TestDocApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestDocApplication.class, args);
	}

}
