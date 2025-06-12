package Opensource_SW_Project.Project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class ProjectApplication {
	// 추가된 줄1
	// 추가된 줄2
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args); // 오류 해결!

	}

}

