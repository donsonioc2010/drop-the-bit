package parking.utils;

import java.util.Scanner;

public class Prompt {
	private static Scanner sc = null;

	public static String getInputString(String title) {
		System.out.print(title);
		return sc.nextLine();
	}

	public static int getInputInt(String title) {
		return Integer.parseInt(getInputString(title));
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
