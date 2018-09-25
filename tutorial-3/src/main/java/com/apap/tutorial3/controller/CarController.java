package com.apap.tutorial3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
	
	@RequestMapping("/car/add")
	public String add(@RequestParam(value="id", required=true) String id,
			@RequestParam(value="brand", required=true) String brand,
			@RequestParam(value="type", required=true) String type,
			@RequestParam(value="price", required=true) Long price,
			@RequestParam(value="amount", required=true) Integer amount) {
		CarModel car = new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
//	@RequestMapping("/car/view")
//	public String view (@RequestParam("id") String id, Model model) {
//		CarModel archive = mobilService.getCarDetail(id);
//		
//		model.addAttribute("car", archive);
//		return "view-car";
//	}

	// Nomor 1
	@RequestMapping("/car/view/{id}")
	public String pathView(@PathVariable String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		
		if (archive == null) {
			model.addAttribute("id", id);
			return "error";
		} else {
			model.addAttribute("car", archive);
			return "view-car";
		}
	}
		
	// Nomor 2
	@RequestMapping("/car/update/{id}/amount/{amount}")
	public String editamount(@PathVariable String id, @PathVariable Integer amount, Model model) {
			CarModel archive = mobilService.updateCarDetail(id, amount);
			if (archive != null) {
//				archive.setAmount(amount);
				return "edit";
			}
			return "view-car";
	}
	

	// Nomor 3
	@RequestMapping("/car/delete/{id}")
	public String editamount(@PathVariable String id, Model model) {
			boolean archive = mobilService.deleteCarDetail(id);
			if (archive == true) {
//				archive.setAmount(amount);
				return "delete";
			}
			return "view-car";
	}
	
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobilService.getCarList();
		
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
}
