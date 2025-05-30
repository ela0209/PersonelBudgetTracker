package model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Lombok ile no-arg constructor oluşturur
@AllArgsConstructor // Lombok ile tüm argümanları içeren constructor oluşturur
public class UserResponse { // 'class' kelimesi fazla, burası 'UserResponse' olmalıydı

    private Long id;
    private String username;
}