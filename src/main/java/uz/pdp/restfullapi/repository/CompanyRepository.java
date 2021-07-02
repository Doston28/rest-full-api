package uz.pdp.restfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.restfullapi.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
