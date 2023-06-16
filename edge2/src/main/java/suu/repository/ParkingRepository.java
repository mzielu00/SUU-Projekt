package suu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suu.model.ParkingSpace;

public interface ParkingRepository extends JpaRepository<ParkingSpace, Long> {

}
