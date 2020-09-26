package com.pe.shotathome.entity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firts_name;
    private String last_name;
    private String phone;
    private String address;
    private String role;
    private String country;
    private String city;
    //
    /*@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;*/

}
