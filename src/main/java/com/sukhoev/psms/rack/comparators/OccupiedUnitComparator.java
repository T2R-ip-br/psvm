package com.sukhoev.psms.rack.comparators;

import com.sukhoev.psms.rack.entity.RackConfiguration;
import com.sukhoev.psms.rack.serviceСlass.OccupiedUnit;

import java.util.Comparator;

public class OccupiedUnitComparator implements Comparator<OccupiedUnit> {

    @Override
    public int compare(OccupiedUnit o1, OccupiedUnit o2) {
        return o2.getUpperUnit() - o1.getUpperUnit();
    }
}
