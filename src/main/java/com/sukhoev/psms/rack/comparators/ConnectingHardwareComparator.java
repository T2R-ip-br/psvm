package com.sukhoev.psms.rack.comparators;

import com.sukhoev.psms.rack.entity.ConnectingHardware;
import com.sukhoev.psms.rack.entity.RackConfiguration;

import java.util.Comparator;

public class ConnectingHardwareComparator implements Comparator<ConnectingHardware> {

    @Override
    public int compare(ConnectingHardware o1, ConnectingHardware o2) {
        return o1.getNamePowerSupplyUnit().compareTo(o2.getNamePowerSupplyUnit());
    }
}
