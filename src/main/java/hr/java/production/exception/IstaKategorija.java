package hr.java.production.exception;

/**
 * exception za odabir iste kategorije vise puta
 */
public class IstaKategorija extends Exception{

    /**
     *konstruktor sa stringom
     * @param message
     */
    public IstaKategorija(String message) {
        super(message);
    }

    /**
     *konstruktor sa throwable
     * @param cause
     */
    public IstaKategorija(Throwable cause) {
        super(cause);
    }

    /**
     *konstruktor sa stringom i throwable
     * @param message
     * @param cause
     */
    public IstaKategorija(String message, Throwable cause) {
        super(message, cause);
    }
}
