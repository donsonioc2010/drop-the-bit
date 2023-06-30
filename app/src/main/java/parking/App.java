package parking;

import parking.process.MemberProcess;
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
        // Input Stream 생성
        Prompt prompt = new Prompt();

        //
        MemberProcess memberProcess = new MemberProcess(prompt);
        memberProcess.login();

        ParkingProcess parkingProcess = new ParkingProcess(prompt, memberProcess.getLoginUser().getMemberType());

        TitlePrintUtils.printMenu(memberProcess.getLoginUser().getMemberType());

        while (true) {
            int menuNo = prompt.getInputInt(Log.getFontPurpleToKeyword("메인") + " > ");
            if (menuNo == 0) {
                TitlePrintUtils.printMenu(memberProcess.getLoginUser().getMemberType());
            } else if (menuNo == 1) {
                parkingProcess.inputParkingCar();
            } else if (menuNo == 2) {
                parkingProcess.printOne();
            } else if (menuNo == 3) {
                parkingProcess.printAllList();
            } else if (menuNo == 4) {
                parkingProcess.updateParkingCar();
            } else if (menuNo == 5) {
                parkingProcess.deleteParkingCar();
            } else if (menuNo == 6) {
                System.out.println("시스템 종료");
                break;
            } else if (menuNo == 7) {
                memberProcess.login();
                parkingProcess.setLoginMemberType(memberProcess.getLoginUser().getMemberType());
            } else if (menuNo == 701) {

            } else if (menuNo == 702) {

            } else if (menuNo >= 900 && menuNo <= 999) {
                memberProcess.adminRunProcess(menuNo);
            } else if (menuNo == -1) {
                //예외처리 Value
            } else if (menuNo == -2) {
                //색상표 확인
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
                System.out.println(Log.getError("정상적인 값을 입력해 주시기 바랍니다."));
            }
        }
    }
}

