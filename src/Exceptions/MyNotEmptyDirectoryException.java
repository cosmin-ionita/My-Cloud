package Exceptions;

import FileSystem.Directory;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class MyNotEmptyDirectoryException extends Exception {
    private Directory exceptionDirectory;
    private String userName;
    private Date time;

    public MyNotEmptyDirectoryException(Directory directory,  String userName, Date time) {
        this.exceptionDirectory = directory;
        this.userName = userName;
        this.time = time;
    }
}
