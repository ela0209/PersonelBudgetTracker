package exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Bu hata fırlatıldığında HTTP 400 kodu döner
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}