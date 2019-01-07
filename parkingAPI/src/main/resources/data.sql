insert into public.vehicle(name, colour, reg_num, vehicle_type) values('Ducati Monster 1200', 'white', 'DL-8CD4455', 1);
insert into public.parking_lot(floor_count) values(3);
insert into public.parking_floor(parking_lot_id, floor_number, rows_count) values(100, 1, 10);
insert into public.parking_row(floor_id, row_number) values(100, 1);
insert into public.parking_slot(row_id, slot_number, status, slot_size, vehicle_id) values(100, 1, 2, 1, 100);