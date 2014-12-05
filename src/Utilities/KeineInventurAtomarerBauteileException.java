package Utilities;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class KeineInventurAtomarerBauteileException extends Exception {
    public static String KIABEX = "KeineInventurAtomarerBauteileException";
    private static final long serialVersionUID = 2560972177738813068L;

    private KeineInventurAtomarerBauteileException() {
        super();
    }

    public static void throwKeineInventurAtomarerBauteileException()
            throws KeineInventurAtomarerBauteileException {
        throw new KeineInventurAtomarerBauteileException();
    }
}
