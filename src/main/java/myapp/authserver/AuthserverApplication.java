package myapp.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AuthserverApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AuthserverApplication.class, args);
		printActiveProfiles(ctx);

		DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		log.info("\n========================================================="
			+ "\n                                                         "
			+ "\n    Authserver                                           "
			+ "\n                                                         "
			+ "\n=========================================================");
	}

	public static String printActiveProfiles(ApplicationContext ctx) {
		StringBuffer sb = new StringBuffer();
		for (final String profileName : ctx.getEnvironment().getActiveProfiles()) {
			log.error("currently active profile={}", profileName);
			sb.append(profileName);
			sb.append(",");
		}

		return sb.toString();
	}

}
