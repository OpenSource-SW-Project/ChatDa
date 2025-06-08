package Opensource_SW_Project.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		// ArrayIndexOutOfBoundsException 해결!
		// hihihello
//		// 1. NullPointereption
//		str.length(); // 런타임 예외 → throws 불필요
//
//		// 2. ArrayIndexOutOfBoundsException
//		int[] arr = new int[3];
//		int value = arr[5]; // 런타임 예외
//
//		// 3. ArithmeticException
//		int result = 10 / 0; // 런타임 예외

		// error 해결 테스트2.1
		// error 해결 테스트3
		// error 해결 테스트4
		// error 해결 테스트5 수정

		// 추가된 줄1
		// 추가된 줄2
	}

}

