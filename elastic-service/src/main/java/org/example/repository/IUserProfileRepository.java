package org.example.repository;


import org.example.repository.entity.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,Long> {

    Optional<UserProfile> findOptionalByAuthid(Long authid);
    Iterable<UserProfile> findByUsernameContaining(String username);
}
