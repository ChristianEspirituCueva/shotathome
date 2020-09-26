package com.pe.shotathome.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String RUC;
    private String phone;
    private String country;
    private String city;
    private String address;
    //
    /*@ManyToOne(fetch = FetchType.LAZY)//carga temprana o tardia(que no se cargue todo al instante y que se cargue despues de forma progresiva)
    @JoinColumn(name = "user_id", nullable=false)//cual va a ser la columna que va a ser la union a nivel de tablas, clave foranea
    private User user;*/

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> product;

}
