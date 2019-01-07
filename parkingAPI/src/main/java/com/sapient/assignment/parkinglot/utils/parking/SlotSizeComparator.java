package com.sapient.assignment.parkinglot.utils.parking;

import com.sapient.assignment.parkinglot.domain.parking.SlotSize;

import java.util.Comparator;

public class SlotSizeComparator implements Comparator<SlotSize> {
    @Override
    public int compare(SlotSize s1, SlotSize s2) {
        return s1.getVal().compareTo(s2.getVal());
    }
}
