package pl.mattcode.chess.util;

public class ConsoleOutput {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    
    public static String color(String output, String color){
        return color+output+RESET;
    }

    public static String color(String output, String color, String background){
        return background+color+output+RESET;
    }

    public static void print(String output, String color){
        System.out.print(ConsoleOutput.color(output, color));
    }

    public static void print(String output, String color, String background){
        System.out.print(ConsoleOutput.color(output, color));
    }
}
