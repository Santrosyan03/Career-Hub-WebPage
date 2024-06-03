package account.database.management.system.mappers;

import account.database.management.system.DTO.CompanyDTO;
import account.database.management.system.DTO.SignUpCompanyDTO;
import account.database.management.system.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDTO toUserDto(Company user);

    @Mapping(target = "password", ignore = true)
    Company signUpToUser(SignUpCompanyDTO signUpDto);

}