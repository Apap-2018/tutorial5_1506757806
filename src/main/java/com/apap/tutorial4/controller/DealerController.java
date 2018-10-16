package com.apap.tutorial4.controller;

import java.util.List;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.repository.DealerDb;
import com.apap.tutorial4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * DealerController
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer, Model model) {
		dealerService.addDealer(dealer);
		model.addAttribute("tipePenambahan", "tambahDealer");	
		return "add";
	}
	
	@RequestMapping(value = "/dealer/view/all", method = RequestMethod.GET)
	private String viewAllDealer(Model model) { 
		DealerDb dealerRepo = dealerService.viewAllDealer();
		List<DealerModel> listDealer = dealerRepo.findAll();
		model.addAttribute("listDealer",listDealer);
		
		return "view-all";
	}
	
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String viewDealer(@RequestParam("dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		List<CarModel> car = dealer.getListCar();
		model.addAttribute("listCar",car);
		model.addAttribute("dealer",dealer);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/delete/{dealerId}", method=RequestMethod.GET)
	private String deleteDealer(@PathVariable(value="dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		dealerService.deleteDealer(dealer);
		return "delete-dealer";
	}
	
	@RequestMapping(value="/dealer/update/{dealerId}", method=RequestMethod.GET)
	private String updateDealer(@PathVariable(value="dealerId") Long dealerId, Model model) {
/*		DealerModel prevDealer = dealerService.getDealerDetailById(dealerId).get();
		DealerModel newDealer = new DealerModel();
		model.addAttribute("previousDealer",prevDealer);
		model.addAttribute("newDealer",newDealer);
		return "update-dealer";
		*/
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		
		if(dealer != null) {
			model.addAttribute("dealer", dealer);
			return "update-dealer";
		} else {
			return "not-found";
		}
	}
	@RequestMapping(value="/dealer/update/submit", method=RequestMethod.POST)
	private String updateDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "success";
	}
	
	/*@RequestMapping(value="/dealer/update", method=RequestMethod.GET)
	public String update(@RequestParam("dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		
		if(dealer != null) {
			model.addAttribute("dealer", dealer);
			return "update-dealer";
		} else {
			return "not-found";
		}
		
	}*/
	
/*	@RequestMapping(value="/dealer/view/{dealerId}", method=RequestMethod.GET)
	private String viewDealer(@PathVariable(value="dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		List<CarModel> carList = dealer.getListCar();
		model.addAttribute("listCar", carList);
		model.addAttribute("dealer", dealer);
		return "view-dealer";
		//return "home";

	}*/
	
	@RequestMapping(value="/dealer/update/submit/{dealerId},method=RequestMethod.POST")
	private String updateDealerSubmit(@PathVariable(value="dealerId") Long id, @ModelAttribute DealerModel dealer) {
		DealerModel old = dealerService.getDealerDetailById(id).get();
		old.setAlamat(dealer.getAlamat());
		old.setNoTelp(dealer.getNoTelp());
		dealerService.addDealer(old);
		return "view-all";
	}
}
