package com.veiculo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.veiculo.entity.Veiculo;
import com.veiculo.exception.VeiculoException;
import com.veiculo.service.VeiculoService;

/**
 * @author ricardo
 *
 */
@Controller
public class VeiculoController {
	
	VeiculoService veiculoService = new VeiculoService();
	
	/**
	 * @param m
	 * @param message
	 * @return
	 */
	@RequestMapping(value={"veiculo/list", "/"})
	public String list(Model m, @RequestParam(required = false) String message){
		List<Veiculo> veiculos = veiculoService.list();
		m.addAttribute("veiculos", veiculos);
		if(message == null){
			message = "";
		}
		m.addAttribute("message", message);
		return "veiculo/list";
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "veiculo/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete( @PathVariable Integer id){
		Veiculo veiculo = null;
		String message = "";
		try {
			veiculo = veiculoService.get(id);
			veiculoService.delete(veiculo);
		}catch(VeiculoException e){
			message = e.getMessage();
		}
		
		if(message.length() < 1){
			message = "Veiculo deletado com sucesso";
		}
		return new ModelAndView("redirect:/", "message", message);
	}
	
	@RequestMapping("veiculo/create")
	public String create(Model m){
		Veiculo veiculo = new Veiculo();
		m.addAttribute("veiculo", veiculo);
		return "veiculo/create";
	}
}
