package uz.pdp.appjwtcard.custom;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.entity.Income;

import java.sql.Timestamp;

@Projection(types = Income.class)
public interface IncomeConfig {

    Integer getId();

    Double getAmount();

    Timestamp getDate();

    Card getFromCard();

    Card getToCard();

}
