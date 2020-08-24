package com.aruerue.shop.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aruerue.shop.dto.addminDto.AddminDto;
import com.aruerue.shop.model.product.Product;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminRepository;

@Controller
public class AddminController {
	
	@Autowired
	private AddminRepository addminRepository;
	
	
	@GetMapping("/list")
	public String list(Model model) {
		System.out.println("list에왔습니다.");
		addminRepository.productList();
		//model.addAttribute("productList",);
		return "addList";
	}
	@GetMapping("/axios")
	public String index() {
		return "index";
	}
	@PostMapping("/crud")
	public @ResponseBody String crud(@RequestBody AddminDto addminDto ) {
		System.out.println("받은값 = "+addminDto);
		
		if(addminDto.getRadioAd().equals("true")) {
			addminDto.setRadioAd2(true);
		}else {
			addminDto.setRadioAd2(false);
		}
		
		
		if(addminDto.getRadioBest().equals("true")) {
			addminDto.setRadioBest2(true);
		}else {
			addminDto.setRadioBest2(false);
		}
		
		if(addminDto.getRadioNew().equals("true")) {
			addminDto.setRadioNew2(true);
		}else {
			addminDto.setRadioNew2(false);
		}
		
		if(addminDto.getRadioSale().equals("true")) {
			addminDto.setRadioSale2(true);
		}else {
			addminDto.setRadioSale2(false);
		}
		
		if(addminDto.getRadioBest().equals("true")) {
			addminDto.setRadioBest2(true);
		}else {
			addminDto.setRadioBest2(false);
		}
		addminRepository.saveProduct(addminDto);
		addminRepository.saveProduct_status(addminDto);
		
		System.out.println("무사히 넣은듯?");
		
		
		return "1";
	}

}
