package com.avent.location;

import com.avent.base.BaseJpaConfig;
import com.avent.base.aspect.AopConfig;
import com.avent.base.config.WebConfig;
import com.avent.base.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.avent.location", "com.avent.audittrail", "com.avent.security"})
@EntityScan(basePackages = {"com.avent.base.entity", "com.avent.location.entity", "com.avent.security.entity"})
@Import({BaseJpaConfig.class, WebConfig.class, AopConfig.class, SecurityConfig.class})
public class ServiceLocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLocationApplication.class, args);
    }

}
