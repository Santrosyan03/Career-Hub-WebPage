package account.database.management.system.exception.global;

public class RepeatedPasswordErrorResponse extends RuntimeException {
    public RepeatedPasswordErrorResponse(String element) {
        super(element + " with this password already exists in database!!!");
    }
}
