package uz.pdp.appjwtcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjwtcard.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
