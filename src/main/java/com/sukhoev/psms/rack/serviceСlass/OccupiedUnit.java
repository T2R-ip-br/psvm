package com.sukhoev.psms.rack.serviceСlass;

import java.util.Objects;

public class OccupiedUnit {

    Integer upperUnit;
    Integer requiredNumberOfUnits;

    public OccupiedUnit(Integer upperUnit, Integer requiredNumberOfUnits) {
        this.upperUnit = upperUnit;
        this.requiredNumberOfUnits = requiredNumberOfUnits;
    }

    public Integer getUpperUnit() {
        return upperUnit;
    }

    public Integer getRequiredNumberOfUnits() {
        return requiredNumberOfUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupiedUnit that = (OccupiedUnit) o;
        return Objects.equals(upperUnit, that.upperUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperUnit);
    }
}
