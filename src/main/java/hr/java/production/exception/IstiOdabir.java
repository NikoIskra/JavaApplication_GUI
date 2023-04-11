package hr.java.production.exception;

/**
 * exception za odabir istih artikala vise puta
 */
public class IstiOdabir extends Exception {
    /**
     * konstruktor sa stringom
     * @param message
     */
    public IstiOdabir(String message) {
        super(message);
    }

    /**+
     * konstruktor sa thrwoable
     * @param cause
     */
    public IstiOdabir(Throwable cause) {
        super(cause);
    }

    /**
     * konstruktor sa stringom i throwable
     * @param message
     * @param cause
     */
    public IstiOdabir(String message, Throwable cause) {
        super(message, cause);
    }
}
