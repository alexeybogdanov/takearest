package com.example.takearest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name", "vote_date"}, name = "unique_vote")})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_name", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "vote_date", nullable = false)
    private LocalDate date;

}
