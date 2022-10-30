package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserProfileSaveRequestDto;
import org.example.dto.request.UserProfileUpdateRequestDto;
import org.example.repository.entity.UserProfile;
import org.example.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.constants.ApiUrls.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    @GetMapping("/getupper")
    public ResponseEntity<String> getUpperCase(Long authid){

        return ResponseEntity.ok(userProfileService.getUpperCase(authid));
    }
    @PostMapping("/savecachable")
    public ResponseEntity<Void> updateUser(@RequestBody UserProfile userProfile){
        userProfileService.updateCacheReset(userProfile);
        return ResponseEntity.ok().build();
    }

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){

        return ResponseEntity.ok(userProfileService.save(dto));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }
    @PostMapping(FIND_BY_ID)
    public ResponseEntity<UserProfile> findById(){
        return null;
    }
    @PostMapping(USER_LIST)
    public ResponseEntity<List<UserProfile>> userList(){
        return null;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserProfile>> getList(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
