package model.request;

// Paket adınızı doğru şekilde güncelleyin

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data // Lombok ile Getter, Setter, toString, equals, hashCode otomatik oluşur
public class UserRegisterRequest {

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3 ile 50 karakter arasında olmalıdır")
    private String username;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 6, max = 100, message = "Şifre en az 6 karakter olmalıdır")
    private String password;
}