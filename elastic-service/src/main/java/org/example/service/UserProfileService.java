package org.example.service;

import org.example.dto.request.UserProfileRequestDto;
import org.example.graphql.model.UserProfileInput;
import org.example.repository.IUserProfileRepository;
import org.example.repository.entity.UserProfile;
import org.example.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void save(UserProfileRequestDto dto){
        save(UserProfile.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .avatar(dto.getAvatar())
                .authid(dto.getAuthid())
                .userid(dto.getId())
                .build());
    }

    public Boolean saveInput(UserProfileInput userProfileInput){
        save(UserProfile.builder().authid(userProfileInput.getAuthid()).username(userProfileInput.getUsername()).email(userProfileInput.getEmail()).build());
        return true;
    }
}
