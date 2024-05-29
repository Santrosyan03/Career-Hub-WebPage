package account.database.management.system.exception.global;

public class RepeatedEmailErrorResponse extends RuntimeException {
    public RepeatedEmailErrorResponse(String element) {
        super(element + " with this email already exists in database!!!");
    }
}
