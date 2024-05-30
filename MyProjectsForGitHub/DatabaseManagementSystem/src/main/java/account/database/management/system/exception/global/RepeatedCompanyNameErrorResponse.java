package account.database.management.system.exception.global;

public class RepeatedCompanyNameErrorResponse extends RuntimeException {
    public RepeatedCompanyNameErrorResponse(String element) {
        super(element + " already exists in database!!!");
    }
}
