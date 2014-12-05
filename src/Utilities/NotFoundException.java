package Utilities;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class NotFoundException extends Exception {
    public static String NFEX = "NotFoundException";
    private static final long serialVersionUID = 2560972177738813068L;

    private NotFoundException() {
        super();
    }

    public static void throwNotFoundException()
            throws NotFoundException {
        throw new NotFoundException();
    }
}
