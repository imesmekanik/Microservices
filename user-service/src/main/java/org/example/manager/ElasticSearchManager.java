package org.example.manager;

import org.example.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import static org.example.constants.ApiUrls.SAVE;
import static org.example.constants.ApiUrls.UPDATE;

@FeignClient(name = "elastic-service",
        url="${myapplication.elastic-service.feign-client}",
        decode404 = true)
public interface ElasticSearchManager {

    @PostMapping(SAVE)
    ResponseEntity<Void> save(@RequestBody UserProfile userProfile);

    @PostMapping(UPDATE)
    ResponseEntity<Void> update(@RequestBody UserProfile userProfile);

}
