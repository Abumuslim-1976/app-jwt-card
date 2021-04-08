package uz.pdp.appjwtcard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    private Double commissionAmount;

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    private Card fromCard;

    @ManyToOne
    private Card toCard;


}
