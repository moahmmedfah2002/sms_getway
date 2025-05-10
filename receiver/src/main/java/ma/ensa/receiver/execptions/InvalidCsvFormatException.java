package ma.ensa.receiver.execptions;

public class InvalidCsvFormatException extends Exception {
    public InvalidCsvFormatException(String message) {
        super(message);
    }

    public InvalidCsvFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
