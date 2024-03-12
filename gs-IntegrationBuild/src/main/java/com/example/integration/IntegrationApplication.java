package com.example.integration;


import com.example.schedulingtasks.ScheduledTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportResource("/integration/integration.xml")
@EnableScheduling
public class IntegrationApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext integrationApplication = new SpringApplication(IntegrationApplication.class).run(args);
		ConfigurableApplicationContext scheduledTasks = new SpringApplication(ScheduledTasks.class).run(args);
		System.out.println("Hit Enter to terminate executions...");
		System.in.read();
		integrationApplication.close();
		scheduledTasks.close();
	}
}