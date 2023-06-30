package parking.utils;

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

    public static String getError() {
        return LogColor.ANSI_BRIGHT_WHITE + LogColor.ANSI_BG_RED + "[ERROR]" + LogColor.ANSI_RESET + " ";
    }

    public static String getError(String message) {
        return getError() + message;
    }

    public static String getMenu(int num, String message) {
        return LogColor.ANSI_GREEN + "[" + num + "] " + LogColor.ANSI_RESET + message;
    }

    public static String getYellowLog(String message) {
        return LogColor.ANSI_YELLOW + message + LogColor.ANSI_RESET;
    }

    public static String bgBlueFgBlack(String message) {
        return LogColor.ANSI_BG_BLUE + LogColor.ANSI_BLACK + message + LogColor.ANSI_RESET;
    }

    public static String getWarn() {
        return LogColor.ANSI_BRIGHT_WHITE + LogColor.ANSI_YELLOW + "[WARN]" + LogColor.ANSI_RESET + " ";
    }

    public static String getWarn(String message) {
        return getWarn() + message;
    }
}
