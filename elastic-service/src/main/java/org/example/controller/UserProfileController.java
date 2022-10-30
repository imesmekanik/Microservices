package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserProfileRequestDto;
import org.example.repository.entity.UserProfile;
import org.example.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.example.contants.ApiUrls.*;

@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfileRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserProfile userProfile){
        userProfileService.save(userProfile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> getAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

}
