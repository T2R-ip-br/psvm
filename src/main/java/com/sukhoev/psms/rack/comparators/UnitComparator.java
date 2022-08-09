package com.sukhoev.psms.rack.comparators;

import com.sukhoev.psms.rack.entity.RackConfiguration;

import java.util.Comparator;

public class UnitComparator implements Comparator<RackConfiguration> {

    @Override
    public int compare(RackConfiguration o1, RackConfiguration o2) {
        return o2.getOccupiedUnit() - o1.getOccupiedUnit();
    }
}
