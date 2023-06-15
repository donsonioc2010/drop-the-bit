package parking;

import parking.process.ParkingProcess;
import parking.utils.Log;
import parking.utils.LogColor;
import parking.utils.Prompt;
import parking.utils.TitlePrintUtils;

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
        TitlePrintUtils.printMenu();
        while (true) {
            int menuNo = Prompt.getInputInt(Log.getFontPurpleToKeyword("메인> "));
            if (menuNo == 0) {
                TitlePrintUtils.printMenu();
            } else if (menuNo == 1) {
                ParkingProcess.inputParkingCar();
            } else if (menuNo == 2) {
                ParkingProcess.printOne();
            } else if (menuNo == 3) {
                ParkingProcess.printAllList();
            } else if (menuNo == 4) {
                ParkingProcess.updateParkingCar();
            } else if (menuNo == 5) {
                ParkingProcess.deleteParkingCar();
            } else if (menuNo == 6) {
                System.out.println("시스템 종료");
                break;
            } else if (menuNo == -1) {
            } else if (menuNo == -2) {

                System.out.println("\n  Default text\n");

                for (String fg : LogColor.FOREGROUNDS) {
                    for (String bg : LogColor.BACKGROUNDS) {
                        System.out.print(fg + bg + "  TEST  ");
                    }
                    System.out.println(LogColor.ANSI_RESET);
                }
                System.out.println();
                for (String fg : LogColor.FOREGROUNDS) {
                    System.out.print(fg + "  TEST  ");
                }

                System.out.println(LogColor.ANSI_RESET + "\n  Back to default.\n");
            } else {
                System.out.println("정상적인 값을 입력해 주세요");
            }
        }
    }
}

