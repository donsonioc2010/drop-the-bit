/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ## 로직
 * ### 관리 데이터 리스트
 * - id : Long
 * - CarNumber : String
 * - PhoneNumber : String
 * - NearInTime\[최근 입차시간\] : LocalDateTime
 * - Amount : Int
 * - isPayment : Boolean
 * ### 행위
 * 1. 입차
 * 2. 출차
 * 3. 출력
 * 4. 수정
 * 5. 출차
 * 6. 종료
 */
public class App {
	public static void main(String[] args) {
		System.out.println("나의 목록 관리 시스템");
		System.out.println("--------------------------------------------------------------------");
		System.out.println(
			String.format("%s\t%s\t%s\t%s\t%s\t%s", "번호", "차량번호", "전화번호", "주차 시작 시간", "주차비용", "결제여부"));

		System.out.print(
			String.format("%04d\t%5s\t%10s\t%s\t%s\t%8s\n", 1, "홍길동", "39두1111",
				LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT.ofPattern("yyyy-MM-dd HH:mm")), 1000,
				true));

	}
}
