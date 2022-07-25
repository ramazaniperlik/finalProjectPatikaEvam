package com.finalproject.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Invoice")
@Data
@DynamicUpdate
@RequiredArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Invoice {

  @Id
  @GeneratedValue
  private int id;

  @Column(name="invoice_amount")
  @Size(max = 50)
  @NotNull
  @NonNull
  private String invoiceAmount;

  @Column(name = "date")
  @NonNull
  private java.sql.Date date;

  @Column(name = "statu")
  @NonNull
  @Size(min = 1,max = 1)
  private String statu;

  @ManyToOne
  @JoinColumn(name = "user_id",referencedColumnName = "id")
  @NonNull
  private User user;

}
