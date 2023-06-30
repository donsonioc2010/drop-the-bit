package parking.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Prompt {
	private Scanner sc;

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
		return Integer.parseInt(getInputString(title));
	}

	public void scClose() {
		sc.close();
	}
}
