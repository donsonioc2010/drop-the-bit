package parking.utils;

import java.util.Scanner;

public class Prompt {
    private static Scanner sc = null;

    public static String getInputString(String title) {
        System.out.print(title);
        return sc.nextLine();
    }

    public static int getInputInt(String title) {
        try {
            return Integer.parseInt(getInputString(title));
        } catch (NumberFormatException e) {
            System.out.println(Log.getError(Log.getConsoleRedColorToKeyword("입력") + "를 " + Log.getConsoleBlueColorToKeyword("숫자만 ") + "입력해 주시기 바랍니다."));
            return -1;
        }
    }

    public static void scClose() {
        sc.close();
    }

    public static void scOpen() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
    }

}
