package org.example.service;

import org.example.dto.request.UserProfileSaveRequestDto;
import org.example.dto.request.UserProfileUpdateRequestDto;
import org.example.exception.ErrorType;
import org.example.exception.UserServiceException;
import org.example.manager.ElasticSearchManager;
import org.example.repository.IUserProfileRepository;
import org.example.repository.entity.UserProfile;
import org.example.utility.JwtTokenManager;
import org.example.utility.ServiceManager;
import org.example.utility.TokenManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager tokenManager;
    private final CacheManager cacheManager;
    private final ElasticSearchManager elasticSearchManager;
    public UserProfileService(IUserProfileRepository iUserProfileRepository,
                              CacheManager cacheManager,
                              ElasticSearchManager elasticSearchManager,
                              JwtTokenManager tokenManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.tokenManager = tokenManager;
        this.cacheManager = cacheManager;
        this.elasticSearchManager = elasticSearchManager;
    }

    @Cacheable(value = "uppercase")
    public String getUpperCase(Long authid) {
        /**
         * Bu kısım methodun belli işlem basamaklarını simüle etmek ve
         * belli zaman alacak işlemleri göstermek için yazılmıştır.
         */
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
        Optional<UserProfile> user = iUserProfileRepository.findOptionalByAuthid(authid);
        if(user.isEmpty()) return "";
        return user.get().getName().toUpperCase();
    }
    public Boolean save(UserProfileSaveRequestDto dto){
        UserProfile userProfile =   save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        elasticSearchManager.save(userProfile);
        return true;
    }
    public Boolean update(UserProfileUpdateRequestDto dto){
        Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
        if(authid.isEmpty()) throw new UserServiceException(ErrorType.GECERSIZ_ID);
        Optional<UserProfile> userProfile =
                iUserProfileRepository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty()) throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UserProfile profile = userProfile.get();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setAvatar(dto.getAvatar());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        save(profile);
        elasticSearchManager.update(profile);
        return true;
    }
    public void updateCacheReset(UserProfile profile){
        save(profile);
        /**
         * Bu işlem ilgili method tarafından tutulan tüm önbeleklenmiş datayı temizler
         * çok istemediğimiz gerekli olduğunda kullanmamız gereken bir yapıdır.
         *  cacheManager.getCache("uppercase")
         */
        cacheManager.getCache("uppercase").evict(profile.getAuthid());
    }


}
