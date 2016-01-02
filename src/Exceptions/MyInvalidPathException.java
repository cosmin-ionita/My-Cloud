package Exceptions;

import FileSystem.Directory;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class MyInvalidPathException extends Exception {

    private Directory exceptionDirectory;
    private String exceptionParameter;
    private String userName;
    private Date time;

    public MyInvalidPathException(Directory directory, String parameter, String userName, Date time) {
        this.exceptionDirectory = directory;
        this.exceptionParameter = parameter;
        this.userName = userName;
        this.time = time;
    }

    public String toString() {
        return "Invalid Path Exception ->> \r\n" + "Directory: " + exceptionDirectory + "\r\n" +
                "Parameter: " + exceptionParameter + "\r\n" +
                "Username: " + userName + "\r\n" +
                "Time: " + time.toString();

    }
}
