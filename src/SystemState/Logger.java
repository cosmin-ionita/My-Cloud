package SystemState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class Logger {

    private static String log = "";

    public static void log(String logMessage) {
        log += logMessage + "\r\n";
    }

    public static void storeLogs() {
        File file = new File("Logs.txt");

        try {
            RandomAccessFile stream = new RandomAccessFile(file, "rw");

            stream.seek(file.length());
            stream.write(log.getBytes());

            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
