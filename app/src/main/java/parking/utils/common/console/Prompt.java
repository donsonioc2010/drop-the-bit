package parking.utils.common.console;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Prompt {
	private Scanner sc;

	public Prompt() {
		this(System.in);
	}

	public Prompt(InputStream in) {
		this.sc = new Scanner(in);
	}

	public String getInputString(String title) throws NoSuchElementException, IllegalStateException {
		System.out.print(title);
		return sc.nextLine();
	}

	public int getInputInt(String title) throws NoSuchElementException, IllegalStateException {
		return Integer.parseInt(getInputString(title));
	}

	public void scClose() {
		sc.close();
	}
}
