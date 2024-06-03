package account.database.management.system.exception.global;

public class IncorrectPasswordErrorResponse extends RuntimeException {
    public IncorrectPasswordErrorResponse() {
        super("Password is incorrect, try again...");
    }
}
