package parking.process;

import static parking.utils.TitlePrintUtils.outputTitle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import parking.domain.ParkingCar;

public class ParkingProcess {
	private static final int SIZE = 3;
	private static Scanner sc;
	private static ParkingCar[] parkInfo = new ParkingCar[SIZE];

	private static int length = 0;

	public static void inputParkingCar() {
		for (int i = 0; i < SIZE; i++) {
			addParkingCar(i);
			length++;
			if (isInputProcessEnd()) {
				break;
			}
		}
	}

	private static void addParkingCar(int idx) {
		System.out.print("차량번호 입력 :");
		String carNumber = sc.nextLine();

		System.out.print("전화번호 입력 :");
		String phoneNumber = sc.nextLine();

		System.out.print("계산 여부 입력 ?(y/N) : ");
		boolean isPayment = sc.nextLine().equalsIgnoreCase("y") ? true : false;

		ParkingCar dto = new ParkingCar();
		dto.setId(idx + 1L);
		dto.setCarNumber(carNumber);
		dto.setPhoneNumber(phoneNumber);
		//Default 일단 1000으로 한다.
		dto.setAmount(1000);
		dto.setParkingStartTime(LocalDateTime.now()
			.format(DateTimeFormatter.ISO_INSTANT.ofPattern("yyyy-MM-dd HH:mm")));
		dto.setPayment(isPayment);
		parkInfo[idx] = dto;
	}

	public static void printList() {
		outputTitle();
		for (int i = 0; i < length; i++) {
			System.out.print(
				String.format("%04d\t%s\t%s\t%s\t%s\t%s\n",
					parkInfo[i].getId(), parkInfo[i].getCarNumber(), parkInfo[i].getPhoneNumber(),
					parkInfo[i].getParkingStartTime(), parkInfo[i].getAmount(), parkInfo[i].isPayment())
			);
		}
	}

	public static void scOpen() {
		sc = new Scanner(System.in);
	}

	public static void scClose() {
		sc.close();
	}

	private static boolean isInputProcessEnd() {
		System.out.print("계속 진행하겠습니까? (n/N) : ");
		if (sc.nextLine().equalsIgnoreCase("n")) {
			return true;
		}
		return false;
	}
}
