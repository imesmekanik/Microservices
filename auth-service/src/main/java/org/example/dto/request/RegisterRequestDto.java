package org.example.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotNull(message ="Kullanici adi zorunludur")
    @Size(min = 3,max=16)
    String username;
    @NotNull(message="Sifre giriniz")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Şifre en az 8 karakterden oluşmalıdır. En az bir büyük harf, bir küçük harf, bir sayı ve bir özel karakter içermelidir.")
    @Size(min=8,max=64)
    String password;
    @Email(message="Geçerli email giriiniz")
    String email;
    String admincode;
}
