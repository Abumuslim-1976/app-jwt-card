package uz.pdp.appjwtcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appjwtcard.custom.OutcomeConfig;
import uz.pdp.appjwtcard.entity.Card;
import uz.pdp.appjwtcard.entity.Outcome;

import java.util.Optional;

@RepositoryRestResource(path = "outcome",excerptProjection = OutcomeConfig.class)
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

}
