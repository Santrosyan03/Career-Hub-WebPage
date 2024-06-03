package account.database.management.system.exception.global;

public class IncorrectEmailErrorResponse extends RuntimeException {
    public IncorrectEmailErrorResponse() {
        super("Email is incorrect, try again...");
    }
}
