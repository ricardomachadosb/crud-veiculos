package com.veiculo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.veiculo.entity.Veiculo;
import com.veiculo.service.VeiculoService;

@Controller
public class SampleController {
	
	VeiculoService veiculoService = new VeiculoService();
	
	@RequestMapping("home")
	public String loadHomePage(Model m) {
		m.addAttribute("name", "CodeTutr");
		
		
		Veiculo veiculo = new Veiculo();
		veiculo.setFabricante("fab");
		veiculo.setAno("1111");
		veiculo.setModelo("mod");
		
		veiculoService.save(veiculo);
		
		
		return "home";
	}
}