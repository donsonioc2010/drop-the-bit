package parking;

import static parking.utils.TitlePrintUtils.processStartTitle;
import static parking.utils.TitlePrintUtils.terminalClear;

import parking.process.ParkingProcess;
import parking.utils.Prompt;

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
		Prompt.scOpen();

		processStartTitle();
		ParkingProcess.inputParkingCar();
		terminalClear();
		ParkingProcess.printList();

		Prompt.scClose();
	}

}

