package suu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suu.model.TrafficCongestion;

public interface TrafficRepository extends JpaRepository<TrafficCongestion, Long> {

}
