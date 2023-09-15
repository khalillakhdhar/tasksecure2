package com.demonstration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.demonstration.dao.TaskRepository;
import com.demonstration.entities.AppRole;
import com.demonstration.entities.AppUser;
import com.demonstration.entities.Task;
import com.demonstration.service.AccountService;

@SpringBootApplication
public class TasksecureApplication implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
	private AccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(TasksecureApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		accountService.saveUser(new AppUser("admin", "1234",1,null));
		accountService.saveUser(new AppUser("user", "1234",1,null));
		accountService.saveRole(new AppRole("ADMIN"));
		accountService.saveRole(new AppRole("USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("user", "USER");
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(null,t)); 
		});
		//affichage
		taskRepository.findAll().forEach(t->{
			System.out.println(t.getTaskName());
		});
	}
@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("http://localhost:4200"); // Allow requests from your Angular app's origin
    config.addAllowedMethod("*"); // Allow all HTTP methods
    config.addAllowedHeader("*"); // Allow all headers
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}

		
}
