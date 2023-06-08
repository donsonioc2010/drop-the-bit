package parking.process;

import static parking.utils.Prompt.getInputString;
import static parking.utils.TitlePrintUtils.outputTitle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parking.domain.ParkingCar;
import parking.utils.Prompt;

public class ParkingProcess {
	private static final int SIZE = 3;
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
		String carNumber = getInputString("차량번호 입력 :");
		String phoneNumber = getInputString("전화번호 입력 :");
		boolean isPayment = getInputString("계산 여부 입력 ?(y/N) : ").equalsIgnoreCase("y") ? true : false;

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
			System.out.printf("%04d\t%s\t%s\t%s\t%s\t%s\n",
				parkInfo[i].getId(),
				parkInfo[i].getCarNumber(),
				parkInfo[i].getPhoneNumber(),
				parkInfo[i].getParkingStartTime(),
				parkInfo[i].getAmount(),
				getPaymentStatusToString(parkInfo[i].isPayment())
			);
		}
	}

	private static boolean isInputProcessEnd() {
		return Prompt.getInputString("계속 진행하겠습니까? (n/N) : ").equalsIgnoreCase("n");
	}

	private static String getPaymentStatusToString(boolean isPayment) {
		return isPayment ? "정산완료" : "미정산";
	}
}
