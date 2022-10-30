package org.example.graphql.query;

import graphql.kickstart.tools.GraphQLQueryResolver;

import lombok.RequiredArgsConstructor;
import org.example.repository.IUserProfileRepository;
import org.example.repository.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver  implements GraphQLQueryResolver {

    private final IUserProfileRepository userProfileRepository;
    public Iterable<UserProfile> findByUsername(String username){
        return userProfileRepository.findByUsernameContaining(username);
    }
    public Iterable<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }
}
