package Exceptions;

import FileSystem.Directory;

import java.util.Date;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class MyPathTooLongException extends MyInvalidPathException {

    public MyPathTooLongException(Directory directory, String parameter, String userName, Date time) {
        super(directory, parameter, userName, time);
    }
}
