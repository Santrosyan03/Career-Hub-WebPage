package account.database.management.system.mappers;

import account.database.management.system.DTO.JobSeekerDTO;
import account.database.management.system.DTO.SignUpJobSeekerDTO;
import account.database.management.system.model.JobSeeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobSeekerMapper {

    JobSeekerDTO toUserDto(JobSeeker user);

    @Mapping(target = "password", ignore = true)
    JobSeeker signUpToUser(SignUpJobSeekerDTO signUpDto);

}
