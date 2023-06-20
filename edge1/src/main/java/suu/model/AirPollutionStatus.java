package suu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AirPollutionStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private int PM10;
  private int PM2_5;
  private int PM1;

  @Override
  public String toString(){
    return "PM10:" + PM10 + ",PM2.5:" + PM2_5 + "PM1:" + PM1;
  }
}
