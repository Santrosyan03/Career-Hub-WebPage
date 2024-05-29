package account.database.management.system.exception.global;

public class NotExistingErrorResponse extends RuntimeException {
    public NotExistingErrorResponse(String element) {
        super(element + " do not exist in database!!!");
    }
}
