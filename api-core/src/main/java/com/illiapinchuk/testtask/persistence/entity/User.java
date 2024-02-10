package com.illiapinchuk.testtask.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.illiapinchuk.testtask.model.entity.RoleName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/** User class represents a user in the db. */
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String firstname;

  String lastname;

  String login;

  String password;

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
  List<Auction> createdAuctions;

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "bidder", cascade = CascadeType.ALL)
  List<Bid> bids;

  @ElementCollection(targetClass = RoleName.class, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "user_role", nullable = false)
  @Enumerated(EnumType.STRING)
  Set<RoleName> roles;
}
