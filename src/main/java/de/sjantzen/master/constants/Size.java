package de.sjantzen.master.constants;

import java.math.BigDecimal;

/**
 * Created by sJantzen on 11.12.2017.
 */
public enum Size {

    SMALL(1, "klein"), MEDIUM(2, "mittel"), LARGE(3, "groß"), ONE_SIZE(4, "Einheitsgröße");

    private int id;
    private String name;

    Size(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
