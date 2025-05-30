package model.request;




import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {

    @NotNull(message = "Kullanıcı ID boş olamaz")
    private Long userId;

    @NotNull(message = "Kategori ID boş olamaz")
    private Long categoryId;

    @NotNull(message = "Tutar boş olamaz")
    @DecimalMin(value = "0.01", message = "Tutar 0'dan büyük olmalıdır")
    private BigDecimal amount;

    @NotBlank(message = "Açıklama boş olamaz")
    @Size(max = 255, message = "Açıklama 255 karakterden uzun olamaz")
    private String description;

    @NotNull(message = "İşlem tarihi boş olamaz")
    private LocalDate transactionDate;

    @NotBlank(message = "İşlem tipi (INCOME/EXPENSE) boş olamaz")
    private String type; // "INCOME" veya "EXPENSE"
}
