package parking.process;

import static parking.utils.Prompt.getInputString;
import static parking.utils.TitlePrintUtils.outputTitle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parking.domain.ParkingCar;
import parking.utils.Prompt;

public class ParkingProcess {
	private static final int SIZE = 50;
	private static ParkingCar[] parkInfo = new ParkingCar[SIZE];
	private static int length = 0;
	private static long userId = 1L;

	public static void inputParkingCar() {
		addParkingCar();
	}

	private static void addParkingCar() {
		if (isCanUseLength()) {
			String carNumber = getInputString("차량번호 입력 :");
			String phoneNumber = getInputString("전화번호 입력 :");
			boolean isPayment = getInputString("계산 여부 입력 ?(y/N) : ").equalsIgnoreCase("y") ? true : false;

			parkInfo[length] = new ParkingCar(userId,
				carNumber, phoneNumber,
				LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT.ofPattern("yyyy-MM-dd HH:mm")),
				1000, isPayment
			);
			userId++;
			length++;
		} else {
			System.out.println("주차장의 사용량이 최대치에 도달하여 더이상 입차를 받지 못합니다.");
		}
	}

	private static int getIdxById(String id) {
		if (length <= 0) {
			return -1;
		}

		try {
			int numId = Integer.parseInt(id);
			for (int i = 0; i < length; i++) {
				if (parkInfo[i].getId() == numId) {
					return i;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println(id + "가 숫자 타입이 아닙니다.");
		}
		printNoInputNo();
		return -1;
	}

	public static void printOne() {
		if (length <= 0) {
			System.out.println("입력된 데이터가 없습니다");
			return;
		}
		int idx = getIdxById(Prompt.getInputString("검색희망 번호>"));
		if (idx > 0) {
			return;
		}
		printParkInfo(parkInfo[idx]);
	}

	public static void printAllList() {
		outputTitle();
		for (int i = 0; i < length; i++) {
			printParkInfo(parkInfo[i]);
		}
	}

	private static void printNoInputNo() {
		System.out.println("없는 번호 입니다.");
	}

	private static void printParkInfo(ParkingCar parkInfo) {
		System.out.printf("%04d\t%s\t%s\t%s\t%s\t%s\n",
			parkInfo.getId(),
			parkInfo.getCarNumber(),
			parkInfo.getPhoneNumber(),
			parkInfo.getParkingStartTime(),
			parkInfo.getAmount(),
			getPaymentStatusToString(parkInfo.isPayment())
		);
	}

	public static void updateParkingCar() {
		int idx = getIdxById(Prompt.getInputString("조회희망 번호>"));
		if (idx < 0) {
			return;
		}
		parkInfo[idx].setCarNumber(getInputString("차량번호 입력 :"));
		parkInfo[idx].setPhoneNumber(getInputString("전화번호 입력 :"));
		parkInfo[idx].setPayment(getInputString("계산 여부 입력 ?(y/N) : ").equalsIgnoreCase("y") ? true : false);
	}

	public static void deleteParkingCar() {
		int idx = getIdxById(Prompt.getInputString("삭제희망 번호>"));
		if (idx < 0) {
			return;
		}

		if (idx == length) {
			parkInfo[idx] = null;
			return;
		}
		for (int i = idx + 1; i < length; i++) {
			parkInfo[i - 1] = parkInfo[i];
		}
		length--;
	}

	private static boolean isCanUseLength() {
		return length < SIZE;
	}

	private static boolean isInputProcessEnd() {
		return Prompt.getInputString("계속 진행하겠습니까? (n/N) : ").equalsIgnoreCase("n");
	}

	private static String getPaymentStatusToString(boolean isPayment) {
		return isPayment ? "정산완료" : "미정산";
	}
}
