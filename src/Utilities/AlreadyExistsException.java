package Utilities;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class AlreadyExistsException extends Exception {
    public static String ALREX = "AlreadyExistsException";
    private static final long serialVersionUID = 2560972177738813068L;

    private AlreadyExistsException() {
        super();
    }

    public static void throwAlreadyExistsException()
            throws AlreadyExistsException {
        throw new AlreadyExistsException();
    }
}
