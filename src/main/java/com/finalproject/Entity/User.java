package com.finalproject.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name= "users")
@Data//Get-set-hashcode
@DynamicUpdate//When updating with Put, it is used to make nulls except for the sent field.
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User {

  @Id
  @GeneratedValue
  private int id;

  @Column(name="first_name")
  @Size(max = 30)
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @Size(max = 30)
  @NotNull
  private String lastName;

  @OneToMany(mappedBy = "user")
  List<Invoice> invoiceList;
}
