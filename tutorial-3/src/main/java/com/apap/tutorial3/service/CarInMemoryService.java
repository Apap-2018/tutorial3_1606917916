package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.CarModel;

import org.springframework.stereotype.Service;


/**
 * CarInMemoryService
 * @author rosatamarindus
 *
 */

@Service
public class CarInMemoryService implements CarService{
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
	}
	
	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
//		return null;
	}
	

	@Override
	public CarModel getCarDetail(String id) {
		for(CarModel item: archiveCar) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	@Override
	public CarModel updateCarDetail(String id, Integer Amount) {
		for (CarModel item: archiveCar) {
			if (item.getId().equals(id)){
				item.setAmount(Amount);
				return item;
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteCarDetail(String id) {
		return archiveCar.remove(getCarDetail(id));
	}	
}
