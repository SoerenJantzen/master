package de.sjantzen.master.constants;

import java.math.BigDecimal;

/**
 * Created by sJantzen on 16.12.2017.
 */
public enum OrderStatus {

    ORDERED(BigDecimal.valueOf(1)), PAYED(BigDecimal.valueOf(2)), PREPARED(BigDecimal.valueOf(3)), PICKED_UP(BigDecimal.valueOf(4));

    private BigDecimal id;

    OrderStatus(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }
}
