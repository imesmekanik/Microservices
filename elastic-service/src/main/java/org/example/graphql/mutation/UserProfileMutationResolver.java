package org.example.graphql.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.example.graphql.model.UserProfileInput;
import org.example.service.UserProfileService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput userProfileInput){
        userProfileService.saveInput(userProfileInput);
        return  true;
    }
}
