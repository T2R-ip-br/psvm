package com.sukhoev.psms.hardware.comparators;

import com.sukhoev.psms.hardware.entity.Hardware;

import java.util.Comparator;

public class HardwareComparator implements Comparator<Hardware> {

    @Override
    public int compare(Hardware o1, Hardware o2) {
        return (int) (o1.getTypeHardware().getId() - o2.getTypeHardware().getId());
    }
}
