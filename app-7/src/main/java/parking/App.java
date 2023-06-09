/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
	static final int SIZE = 3;

	public static void main(String[] args) {

		// S : 선언부
		int length = 0;
		Scanner sc = new Scanner(System.in);
		long[] id = new long[SIZE];
		String[] carNumber = new String[SIZE];
		String[] phoneNumber = new String[SIZE];
		int[] amount = new int[SIZE];
		String[] parkingStartTime = new String[SIZE];
		boolean[] isPayment = new boolean[SIZE];

		processStartTitle();

		//입력
		for (int i = 0; i < SIZE; i++) {
			addParkingCar(sc, i, id, carNumber, phoneNumber, parkingStartTime, amount, isPayment);
			length++;
			if (isInputProcessEnd(sc)) {
				break;
			}
		}
		terminalClear();
		printList(length, id, carNumber, phoneNumber, parkingStartTime, amount, isPayment);
		sc.close();
	}

	static boolean isInputProcessEnd(Scanner sc) {
		System.out.print("계속 진행하겠습니까? (n/N) : ");
		if (sc.nextLine().equalsIgnoreCase("n")) {
			return true;
		}
		return false;
	}

	static void addParkingCar(
		Scanner sc,
		int idx,
		long[] id,
		String[] carNumber,
		String[] phoneNumber,
		String[] parkingStartTime,
		int[] amount,
		boolean[] isPayment
	) {
		id[idx] = idx + 1L;

		System.out.print("차량번호 입력 :");
		carNumber[idx] = sc.nextLine();

		System.out.print("전화번호 입력 :");
		phoneNumber[idx] = sc.nextLine();

		//Default 일단 1000으로 한다.
		amount[idx] = 1000;

		parkingStartTime[idx] = LocalDateTime.now()
			.format(DateTimeFormatter.ISO_INSTANT.ofPattern("yyyy-MM-dd HH:mm"));

		System.out.print("계산 여부 입력 ?(y/N) : ");
		isPayment[idx] = sc.nextLine().equalsIgnoreCase("y") ? true : false;

	}

	static void processStartTitle() {
		System.out.println("차량 목록 관리 시스템");
		System.out.println("--------------------------------------------------------------------");
	}

	static void terminalClear() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

	static void outputTitle() {
		System.out.println(
			String.format("%s\t%s\t%s\t%s\t%s\t%s",
				"번호", "차량번호", "휴대폰 전화번호", "주차 시작 시간", "주차비용", "결제여부"));
		System.out.println("--------------------------------------------------------------------");
	}

	static void printList(int length,
		long[] id,
		String[] carNumber,
		String[] phoneNumber,
		String[] parkingStartTime,
		int[] amount,
		boolean[] isPayment
	) {
		outputTitle();
		for (int i = 0; i < length; i++) {
			System.out.print(
				String.format("%04d\t%s\t%s\t%s\t%s\t%s\n",
					id[i], carNumber[i], phoneNumber[i], parkingStartTime[i], amount[i], isPayment[i])
			);
		}
	}

}
