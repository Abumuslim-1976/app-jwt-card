package uz.pdp.appjwtcard.custom;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.entity.Outcome;

import java.sql.Timestamp;

@Projection(types = Outcome.class)
public interface OutcomeConfig {

    Integer getId();

    Double getAmount();

    Double getCommissionAmount();

    Timestamp getDate();

    Card getFromCard();

    Card getToCard();

}
