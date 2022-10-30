package org.example.utility;


import lombok.RequiredArgsConstructor;
import org.example.manager.IUserProfileManager;
import org.example.repository.entity.UserProfile;
import org.example.service.UserProfileService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final IUserProfileManager userProfileManager;
    private final UserProfileService userProfileService;
    //@PostConstruct
    public void firstRun(){
        List<UserProfile> userProfiles = userProfileManager.userList().getBody();
        userProfiles.forEach(userProfile -> {
            userProfile.setId(null);
            userProfile.setUserid(Long.getLong(userProfile.getId()));
            userProfileService.save(userProfile);
        });
    }
}
