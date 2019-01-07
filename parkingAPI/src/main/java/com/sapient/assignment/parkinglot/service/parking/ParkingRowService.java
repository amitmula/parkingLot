package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.*;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.repository.parking.ParkingRowRepo;
import com.sapient.assignment.parkinglot.utils.parking.SlotSizeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ParkingRowService {

    @Autowired
    ParkingRowRepo parkingRowRepo;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Value("${parkingRow.slots.small}")
    private String smallSlotsCount;

    @Value("${parkingRow.slots.medium}")
    private String mediumSlotsCount;

    @Value("${parkingRow.slots.large}")
    private String largeSlotsCount;

    public ParkingRow getRowById(Integer id) {
        return parkingRowRepo.getById(id);
    }

    public ParkingRow createParkingRow(ParkingRow parkingRow) {
        return parkingRowRepo.createParkingRow(parkingRow);
    }

    public ParkingSlot park(Vehicle vehicle, ParkingRow row) {
        List<ParkingSlot> rowSlots = parkingSlotService.getAllSlots(row);

        List<SlotSize> slotSizes = Arrays.stream(SlotSize.values())
            .filter(size -> size.isVehicleParkingPossible(vehicle))
            .sorted(new SlotSizeComparator())
            .collect(Collectors.toList());

        List<ParkingSlot> parkingSlots = rowSlots.stream()
            .filter(slot -> slotSizes.contains(slot.getSlotSize()) && slot.getStatus().equals(ParkingStatus.AVAILABLE))
            .sorted()
            .collect(Collectors.toList());

        if(parkingSlots.isEmpty()) {
            Map<SlotSize, Integer> slotCounts = Arrays.stream(SlotSize.values()).filter(slotSize -> slotSizes.contains(slotSize))
                .collect(Collectors.toMap(Function.identity(), e -> parkingSlotService.getRowSlotsCountBySize(e, row)));
            ParkingSlot slotToAllocate = null;
            for(SlotSize slotSize : slotCounts.keySet().stream().sorted().collect(Collectors.toList())) {
                if(slotSize.name().equalsIgnoreCase("SMALL")
                    && slotCounts.get(slotSize) < Integer.parseInt(smallSlotsCount)) {
                    slotToAllocate = createParkingSlot(vehicle, row, slotCounts.get(slotSize) + 1, slotSize);
                    break;
                } else if(slotSize.name().equalsIgnoreCase("MEDIUM")
                    && slotCounts.get(slotSize) < Integer.parseInt(mediumSlotsCount)) {
                    slotToAllocate = createParkingSlot(vehicle, row, slotCounts.get(slotSize) + 1, slotSize);
                    break;
                } else {
                    slotToAllocate = createParkingSlot(vehicle, row, slotCounts.get(slotSize) + 1, slotSize);
                    break;
                }
            }
            return slotToAllocate;
        } else {
            ParkingSlot parkingSlot = parkingSlots.get(0);
            parkingSlot.setVehicle(vehicle);
            parkingSlot.setStatus(ParkingStatus.OCCUPIED);
            return parkingSlot;
        }
    }

    private ParkingSlot createParkingSlot(Vehicle vehicle, ParkingRow row, Integer slotNumber, SlotSize slotSize) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setParkingRow(row);
        parkingSlot.setVehicle(vehicle);
        parkingSlot.setSlotNumber(slotNumber);
        parkingSlot.setSlotSize(slotSize);
        parkingSlot.setStatus(ParkingStatus.OCCUPIED);
        return parkingSlotService.createParkingSlot(parkingSlot);
    }

    public List<ParkingRow> getAvailableRows(ParkingFloor parkingFloor) {
        return parkingRowRepo.getAvailableRows(parkingFloor);
    }
}
