package account.database.management.system.DTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSeekerDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private char[] password;
    private String token;

}
