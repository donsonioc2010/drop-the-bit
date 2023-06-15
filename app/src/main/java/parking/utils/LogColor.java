package parking.utils;

public class LogColor {

    public static void logRedToLine(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    public static void logGreenToLine(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void logBlueToLine(String message) {
        System.out.println("\u001B[34m" + message + "\u001B[0m");
    }

    public static void logRedToKeyword(String keyword) {
        System.out.print("\u001B[31m" + keyword + "\u001B[0m");
    }

    public static void logGreenToKeyword(String keyword) {
        System.out.print("\u001B[32m" + keyword + "\u001B[0m");
    }

    public static void logBlueToKeyword(String keyword) {
        System.out.print("\u001B[34m" + keyword + "\u001B[0m");
    }

    public static String getConsoleRedColorToKeyword(String keyword) {
        return "\u001B[31m" + keyword + "\u001B[0m";
    }

    public static String getConsoleGreenColorToKeyword(String keyword) {
        return "\u001B[32m" + keyword + "\u001B[0m";
    }

    public static String getConsoleBlueColorToKeyword(String keyword) {
        return "\u001B[34m" + keyword + "\u001B[0m";
    }

    public static String getConsolePurpleColorToKeyword(String keyword) {
        return "\u001B[35m" + keyword + "\u001B[0m";
    }
}
