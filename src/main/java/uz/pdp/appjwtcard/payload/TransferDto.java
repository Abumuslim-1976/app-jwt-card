package uz.pdp.appjwtcard.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appjwtcard.entity.Card;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private Integer fromCardId;
    private Integer toCardId;
    private Double amount;
}
