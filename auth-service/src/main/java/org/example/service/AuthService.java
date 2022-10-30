package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.LoginRequestDto;
import org.example.dto.request.RegisterRequestDto;
import org.example.dto.request.UserProfileSaveRequestDto;
import org.example.exception.AuthServiceException;
import org.example.exception.ErrorType;
import org.example.manager.UserProfileManager;
import org.example.repository.IAuthRepository;
import org.example.repository.entity.Auth;
import org.example.repository.enums.Roles;
import org.example.utility.JwtTokenManager;
import org.example.utility.ServiceManager;
import org.example.utility.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;

    private final UserProfileManager userProfileManager;
    private final JwtTokenManager tokenManager;

    public  AuthService(IAuthRepository repository,UserProfileManager userProfileManager,JwtTokenManager tokenManager){
        super(repository);
        this.repository=repository;
        this.userProfileManager=userProfileManager;
        this.tokenManager=tokenManager;
    }

    public Boolean save(RegisterRequestDto dto){
        Auth auth = Auth.builder().username(dto.getUsername()).password(dto.getPassword()).email(dto.getEmail()).roles(Roles.ROLE_USER).build();
        if(dto.getAdmincode()!=null) {
            if (dto.getAdmincode().equals("Admin"))
                auth.setRoles(Roles.ROLE_ADMIN);
        }

            save(auth);


        if(auth.getId()!=null){
            userProfileManager.save(UserProfileSaveRequestDto.builder()
                    .authid(auth.getId())
                    .email(dto.getEmail())
                    .username(dto.getUsername()).build());
        }
        return false;
    }

    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);
        return tokenManager.createToken(auth.get().getId());

    }

}
