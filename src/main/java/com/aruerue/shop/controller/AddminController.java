package com.aruerue.shop.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.resource.HttpResource;

import com.aruerue.shop.addminDto.AddminDto;
import com.aruerue.shop.controller.dto.ProductDto;
import com.aruerue.shop.controller.dto.ResponseDto;
import com.aruerue.shop.model.product.Product;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminRepository;

@Controller
//@MultipartConfig(maxFileSize=1024*1024*2,location = "c:\\upload")
public class AddminController{

	@Autowired
	private AddminRepository addminRepository;

//	@Value("${file.path}")
//	private String fileRealPath;

	private AddminDto addminDto;
	@GetMapping("/list2")
	public @ResponseBody List<Product> list2(Model model) {
	addminRepository.productList();
		System.out.println("결과 = "+addminRepository.productList());
		model.addAttribute("productList",addminRepository.productList());
		System.out.println("왓니");
		return addminRepository.productList();
	}



	@GetMapping("/list")
	public String list(Model model) {

	addminRepository.productList();
		System.out.println("결과 = "+addminRepository.productList());
		model.addAttribute("productList",addminRepository.productList());
		System.out.println("왓니");
		return "addList";
	}

	@GetMapping("/axios")
	public String index() {
		System.out.println("axios 탓다");
		return "index";
	}

	@PostMapping("/crud")
	public @ResponseBody String crud(@RequestBody AddminDto addminDto) {
		System.out.println("crud에 쳐왔다");
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
		//values(#{title},#{thumb},#{price},#{disc},#{radioAd2},#{discounted},#{content},#{radioParentTypeId},#{bgImg})
		System.out.println("다하고 나서 title = "+addminDto.getTitle());
		System.out.println("다하고 나서 thumb = "+addminDto.getThumb());
		System.out.println("다하고 나서 price = "+addminDto.getPrice());
		System.out.println("다하고 나서 disc = "+addminDto.getDisc());
		System.out.println("다하고 나서 radioAd2 = "+addminDto.isRadioBest2());

		System.out.println("다하고 나서 discounted = "+addminDto.getDiscounted());
		System.out.println("다하고 나서 content = "+addminDto.getContent());
		System.out.println("다하고 나서 radioParentTypeId = "+addminDto.getRadioParentTypeId());
		System.out.println("다하고 나서 bgImg = "+addminDto.getBgImg());

		System.out.println(addminDto.getBgImg().length());
		addminRepository.saveProduct(addminDto);
		addminRepository.saveProduct_status(addminDto);

		System.out.println("무사히 넣은듯?");


		return "Ok";
	}

	//등록물품 삭제하는곳
	@DeleteMapping("/listDelete/{id}")
	public @ResponseBody String listDelete(@PathVariable int id) {
		System.out.println("listDelete에 왔쪄용!!");
		System.out.println("받은 id = "+id);
		addminRepository.listdelete(id);
		System.out.println("성공한거같은데 ㅅㅂ ");
		return "Ok";
	}
	//등록물품
	@PutMapping("/change/{id}")
	public @ResponseBody String updata(@PathVariable int id , @RequestBody Product product) {
		System.out.println("체인지에왔음");

		System.out.println("product = "+product);
		addminRepository.updata(product);



		return "OK";

	}


}
