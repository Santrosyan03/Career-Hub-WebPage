package account.database.management.system.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
    private UUID id;
    private String companyName;
    private String contactPersonFullName;
    private String country;
    private String city;
    private String phoneNumber;
    private String industry;
    private String email;
    private String token;

}