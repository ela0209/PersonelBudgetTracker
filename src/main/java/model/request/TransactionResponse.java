package model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate transactionDate;
    private String type;
    private String categoryName; // İşlemle ilişkili kategorinin adını göstermek için
    private String username; // İşlemle ilişkili kullanıcının adını göstermek için
}
