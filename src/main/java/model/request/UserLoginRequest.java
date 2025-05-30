package model.request;

// Paket adınızı doğru şekilde güncelleyin

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserLoginRequest {

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    private String username;

    @NotBlank(message = "Şifre boş olamaz")
    private String password;
}