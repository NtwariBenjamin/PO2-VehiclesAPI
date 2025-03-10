package com.udacity.vehicles.client.prices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private String currency;
    private BigDecimal price;
    private Long vehicleId;

//    public Price() {
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public Long getVehicleId() {
//        return vehicleId;
//    }
//
//    public void setVehicleId(Long vehicleId) {
//        this.vehicleId = vehicleId;
//    }
}
