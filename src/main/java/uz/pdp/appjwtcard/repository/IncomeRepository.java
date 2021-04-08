package uz.pdp.appjwtcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appjwtcard.entity.Income;
import uz.pdp.appjwtcard.custom.IncomeConfig;

@RepositoryRestResource(path = "income", excerptProjection = IncomeConfig.class)
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
