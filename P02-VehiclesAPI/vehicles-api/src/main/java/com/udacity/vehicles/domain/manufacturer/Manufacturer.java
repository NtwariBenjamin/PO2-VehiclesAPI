package com.udacity.vehicles.domain.manufacturer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Declares class to hold car manufacturer information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manufacturer {

    @Id
    private Integer code;
    private String name;

//    public Manufacturer() { }
//
//    public Manufacturer(Integer code, String name) {
//        this.code = code;
//        this.name = name;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public String getName() {
//        return name;
//    }
}
