package com.pe.shotathome.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private String RUC;
    private String phone;
    private String country;
    private String city;
    private String address;
}
