package account.database.management.system.DTO;

public record SignUpJobSeekerDTO (String email,
                                  String firstName,
                                  String lastName,
                                  String dateOfBirth,
                                  String gender,
                                  char[] password) { }
