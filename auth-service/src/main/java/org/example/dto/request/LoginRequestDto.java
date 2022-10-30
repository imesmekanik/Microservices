package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotNull(message = "Kullanici adi zorunlu")
    @Size(min=3,max=16)
    String username;
    @NotNull(message = "Şifre zorunlu")
    @Size(min=8,max=64)
    String password;

}
