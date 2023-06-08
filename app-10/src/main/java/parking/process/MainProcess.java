package parking.process;

import static parking.utils.TitlePrintUtils.printMenu;
import static parking.utils.TitlePrintUtils.printSelectMenu;
import static parking.utils.TitlePrintUtils.terminalClear;

import java.util.HashMap;
import java.util.Map;

import parking.utils.Prompt;

public class MainProcess {
	private static Map<Integer, Runnable> menuMap = new HashMap<>();

	static {
		menuMap.put(0, () -> {
			terminalClear();
			printMenu();
		});
		menuMap.put(1, () -> ParkingProcess.inputParkingCar());
		menuMap.put(2, () -> ParkingProcess.printOne());
		menuMap.put(3, () -> {
			terminalClear();
			ParkingProcess.printAllList();
		});
		menuMap.put(4, () -> ParkingProcess.updateParkingCar());
		menuMap.put(5, () -> ParkingProcess.deleteParkingCar());
	}

	public static void mainProcess() {
		boolean isRunning = true;
		printMenu();
		while (isRunning) {
			int menuNo = Prompt.getInputInt("메인> ");
			if (menuNo == 6) {
				isRunning = false;
				continue;
			}
			if (menuMap.containsKey(menuNo)) {
				menuMap.get(menuNo).run();
			} else {
				printSelectMenu();
			}
		}
	}
}
