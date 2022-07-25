package com.finalproject.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name ="payment")
@Data
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name="total_amount")
  @NonNull
  private double totalAmount;

  @Column(name="date")
  @NonNull
  private java.sql.Date date;

  @OneToOne
  @NonNull
  @JoinColumn(name = "user_id",referencedColumnName = "id")
  private User user;
}
