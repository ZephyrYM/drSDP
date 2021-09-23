package Util;

public class Constants {

    public static int GRID_DIMENSION = 10;
    public static int MILLISECONDS_TO_SLEEP = 5000;
    public static String IP_DRONES = "localhost";
    public static String SERVER_ADMIN_ADDRESS = "http://localhost:1337";
    public static String DRONAZON_TOPIC = "dronazon/smartcity/orders/";
    public static String DRONAZON_BROKER = "tcp://localhost:1883";
    public static String OK_MESSAGE = "OK";
    public static String NO_MESSAGE = "NO";
    public static int BATTERY_LEVEL_RED_LIMIT = 15;
    public static String NO_BATTERY_MESSAGE = "NO BATTERY";
    public static String EXIT_MESSAGE = "exit";
    public static String QUIT_MESSAGE = "quit";
    public static int PING_TIME_TO_SLEEP = 60000;
    public static int WINDOW_DIMENSION = 8;
    public static float WINDOW_OVERLAP = 0.5f;


    public enum MessageType {
        WELCOME,
        ORDER,
        STATS,
        ELECTION,
        ELECTED,
        INFO,
        PING,
        ASKCHARGE,
        CONFIRMCHARGE,
        ENDCHARGE
    }
}
