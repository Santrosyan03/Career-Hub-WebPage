package account.database.management.system.DTO;

public record SignUpCompanyDTO (String companyName,
                         String contactPersonFullName,
                         String country,
                         String city,
                         String phoneNumber,
                         String industry,
                         String email,
                         char[] password) { }