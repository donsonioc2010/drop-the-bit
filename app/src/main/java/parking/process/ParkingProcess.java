package parking.process;

import java.time.LocalDateTime;

import parking.domain.ParkingCar;
import parking.type.MemberType;
import parking.utils.Log;
import parking.utils.Prompt;
import parking.utils.TitlePrintUtils;

public class ParkingProcess {
    private static final int SIZE = 50;
    private ParkingCar[] parkInfo;
    private int length = 0;
    private long userId = 1L;

    private Prompt prompt;

    private MemberType loginMemberType;

    public ParkingProcess(Prompt prompt, MemberType loginMemberType) {
        this.prompt = prompt;
        this.loginMemberType = loginMemberType;
        this.printAuthType();

        parkInfo = new ParkingCar[SIZE];
    }

    public void inputParkingCar() {
        addParkingCar();
    }

    private void addParkingCar() {
        if (isCanUseLength()) {
            String carNumber = prompt.getInputString(Log.getFontCyanToKeyword("[차량번호 입력]") + " > ");
            String phoneNumber = prompt.getInputString(Log.getFontCyanToKeyword("[전화번호 입력]:"));
            boolean isPayment = prompt.getInputString(Log.getFontCyanToKeyword("[계산 여부 입력(Y/N)]") + " > ")
                .equalsIgnoreCase("y") ? true : false;

            parkInfo[length] = new ParkingCar(
                userId,
                carNumber,
                phoneNumber,
                LocalDateTime.now(),
                1000,
                isPayment
            );
            userId++;
            length++;
        } else {
            System.out.println(Log.getError("주차장의 사용량이 최대치에 도달하여 더이상 입차를 받지 못합니다."));
        }
    }

    private int getIdxById(String id) {
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

    public void printOne() {
        if (length <= 0) {
            System.out.println("입력된 데이터가 없습니다");
            return;
        }
        int idx = getIdxById(prompt.getInputString("검색희망 번호>"));
        if (idx > 0) {
            return;
        }
        printParkInfo(parkInfo[idx]);
    }

    public void printAllList() {
        TitlePrintUtils.outputTitle();
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

    public void updateParkingCar() {
        int idx = getIdxById(prompt.getInputString("조회희망 번호>"));
        if (idx < 0) {
            return;
        }
        parkInfo[idx].setCarNumber(prompt.getInputString("차량번호 입력 :"));
        parkInfo[idx].setPhoneNumber(prompt.getInputString("전화번호 입력 :"));
        parkInfo[idx].setPayment(prompt.getInputString("계산 여부 입력 ?(y/N) : ").equalsIgnoreCase("y") ? true : false);
    }

    public void deleteParkingCar() {
        int idx = getIdxById(prompt.getInputString("삭제희망 번호>"));
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

    private boolean isCanUseLength() {
        return length < SIZE;
    }

    private boolean isInputProcessEnd() {
        return prompt.getInputString("계속 진행하겠습니까? (n/N) : ").equalsIgnoreCase("n");
    }

    private static String getPaymentStatusToString(boolean isPayment) {
        return isPayment ? "정산완료" : "미정산";
    }

    private void printAuthType() {
        System.out.println();
        System.out.println(Log.getFontBlueToKeyword("[로그인 권한] ")
            + Log.getFontRedToKeyword(loginMemberType.name()));
        System.out.println();
    }

    public void setLoginMemberType(MemberType loginMemberType) {
        this.loginMemberType = loginMemberType;
        this.printAuthType();
    }
}
