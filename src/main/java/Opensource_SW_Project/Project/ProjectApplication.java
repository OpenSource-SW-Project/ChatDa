package Opensource_SW_Project.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ProjectApplication {






	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args); // error 해결!!!!!!!!@!@!@
		// commit action 테스트 1 // test1 commit하기!
		// commit action 테스트 2 // commit하기!
		// commit action 테스트 3 // test@@@@@@@@@@@@@ commit하기!
		// ArrayIndexOutOfBoundsException 강제로 발생시키기 - 코드 블럭 저장!!!
		int[] testArray = new int[2];
		System.out.println(testArray[1]); // ArrayIndexOutOfBoundsException 해결!!!!
		// 코드블럭 테스트1!!
	}

}

