package ma.ensa.receiver.execptions;

public class PhoneNumberExistsException extends Exception {
    public PhoneNumberExistsException(String message) {
        super(message);
    }
}
