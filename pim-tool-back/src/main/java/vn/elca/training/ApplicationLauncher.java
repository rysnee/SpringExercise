package vn.elca.training;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.AbstractEnvironment;

/**
 * @author vlp
 *
 */
public class ApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWebConfig.class, args);
    }
}
