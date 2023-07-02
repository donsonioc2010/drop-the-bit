package parking.utils.log;

public class Log {
	public static String getFontRedToKeyword(String keyword) {
		return LogColor.ANSI_RED + keyword + LogColor.ANSI_RESET;
	}

	public static String getFontGreenToKeyword(String keyword) {
		return LogColor.ANSI_GREEN + keyword + LogColor.ANSI_RESET;
	}

	public static String getFontBlueToKeyword(String keyword) {
		return LogColor.ANSI_BLUE + keyword + LogColor.ANSI_RESET;
	}

	public static String getFontCyanToKeyword(String keyword) {
		return LogColor.ANSI_CYAN + keyword + LogColor.ANSI_RESET;
	}

	public static String getFontPurpleToKeyword(String keyword) {
		return LogColor.ANSI_BRIGHT_PURPLE + keyword + LogColor.ANSI_RESET;
	}

	public static String error() {
		return LogColor.ANSI_BRIGHT_WHITE + LogColor.ANSI_BG_RED + "[ERROR]" + LogColor.ANSI_RESET + " ";
	}

	public static String error(String message) {
		return error() + message;
	}

	public static String warn() {
		return LogColor.ANSI_BRIGHT_WHITE + LogColor.ANSI_YELLOW + "[WARN]" + LogColor.ANSI_RESET + " ";
	}

	public static String warn(String message) {
		return warn() + message;
	}

	public static String info() {
		return LogColor.ANSI_BRIGHT_WHITE + LogColor.ANSI_BG_GREEN + "[INFO]" + LogColor.ANSI_RESET + " ";
	}

	public static String info(String message) {
		return info() + message;
	}
}
