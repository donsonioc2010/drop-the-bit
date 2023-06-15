package parking.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {
    private Scanner sc = null;

    public Prompt() {
        this(System.in);
    }

    public Prompt(InputStream in) {
        this.sc = new Scanner(in);
    }

    public String getInputString(String title) {
        System.out.print(title);
        return sc.nextLine();
    }

    public int getInputInt(String title) {
        try {
            return Integer.parseInt(getInputString(title));
        } catch (NumberFormatException e) {
            System.out.println(Log.getError(Log.getFontRedToKeyword("입력") + "를 "
                + Log.getFontBlueToKeyword("숫자만 ") + "입력해 주시기 바랍니다."));
            return -1;
        }
    }

    public void scClose() {
        sc.close();
    }
}
