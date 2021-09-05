package com.project.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.examportal.exception.UserFoundException;
import com.project.examportal.model.Role;
import com.project.examportal.model.User;
import com.project.examportal.model.UserRole;
import com.project.examportal.repository.QuizRepository;
import com.project.examportal.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner{
	
	@Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuizRepository quizRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
            System.out.println("starting code");

            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setUsername("Admin");
            user.setPassword(this.bCryptPasswordEncoder.encode("access"));
            user.setEmail("admin@gmail.com");

            Role role1 = new Role();
            role1.setRoleId(44L);
            role1.setRoleName("ADMIN");

            Set<UserRole> userRoleSet = new HashSet<>();
            UserRole userRole = new UserRole();
            userRole.setRole(role1);
            userRole.setUser(user);
            userRoleSet.add(userRole);
            User user1 = this.userService.createUser(user, userRoleSet);
            System.out.println(user1.getUsername());

        } catch (UserFoundException e) {
            e.printStackTrace();


        }


    }

}
