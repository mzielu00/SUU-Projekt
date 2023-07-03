package suu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suu.model.AirPollutionStatus;

public interface AirPollutionRepository extends JpaRepository<AirPollutionStatus, Long> {

}
