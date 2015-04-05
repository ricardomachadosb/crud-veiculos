package com.veiculo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.veiculo.entity.Veiculo;
import com.veiculo.exception.VeiculoException;
import com.veiculo.service.FileService;
import com.veiculo.service.VeiculoService;

/**
 * @author ricardo
 *
 */
@Controller
public class VeiculoController {
	
	VeiculoService veiculoService = new VeiculoService();
	FileService fileService = new FileService();
	
	@Autowired
	ServletContext servletContext;
	
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
	
	/**
	 * @param m
	 * @return
	 */
	@RequestMapping("veiculo/create")
	public String create(Model m){
		Veiculo veiculo = new Veiculo();
		m.addAttribute("veiculo", veiculo);
		return "veiculo/create";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("veiculo/save")
	public Object save(Model m, @RequestParam(required = false) String fabricante, 
			@RequestParam(required = false) String modelo,
			@RequestParam(required = false) String ano,
			@RequestParam(required = false) MultipartFile foto){
		
		String message = "";
		Veiculo veiculo = new Veiculo(ano, fabricante, modelo, foto.getOriginalFilename());
		
		try{
			veiculoService.save(veiculo);
		}catch(Exception e){
			message = "Problemas ao salvar novo veículo, verifique os valores informados e tente novamente";
		}
		
		//String filePath = servletContext.getRealPath("/resources/") + "/images/" + foto.getOriginalFilename();
		
		if(foto.getOriginalFilename().length() > 0){
			try{
				fileService.saveImage(foto);
			}catch(Exception e){
				message = "Problemas ao salvar o arquivo";
			}
		}
		
		if(message.length() > 0){
			m.addAttribute("veiculo", veiculo);
			m.addAttribute("message", message);
			return "veiculo/create";
		}
		
		message = "Veiculo salvo com sucesso";
		return new ModelAndView("redirect:/", "message", message);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "veiculo/edit/{id}", method = RequestMethod.GET)
	public Object edit( @PathVariable Integer id, Model m){
		Veiculo veiculo = null;
		String message = "";
		try {
			veiculo = veiculoService.get(id);
		}catch(VeiculoException e){
			message = e.getMessage();
		}
		
		if(message.length() > 0){
			return new ModelAndView("redirect:/", "message", message);
		}
		m.addAttribute("veiculo", veiculo);
		return "veiculo/edit";
	}
	
	/**
	 * @param m
	 * @param fabricante
	 * @param modelo
	 * @param ano
	 * @param foto
	 * @param id
	 * @return
	 */
	@RequestMapping("veiculo/update")
	public Object update(Model m, @RequestParam(required = false) String fabricante, 
			@RequestParam(required = false) String modelo,
			@RequestParam(required = false) String ano,
			@RequestParam(required = false) MultipartFile foto,
			@RequestParam(required = false) Integer id){
		
		String message = "";
		Veiculo veiculo = null;
		
		try{
			veiculo = veiculoService.get(id);
			veiculoService.updateVeiculo(veiculo, fabricante, ano, modelo, foto);
		}catch(Exception e){
			e.printStackTrace();
			message = "Problemas ao alterar veiculo, verifique os valores informado e tente novamente";
		}
		
		if(message.length() > 0){
			m.addAttribute("veiculo", veiculo);
			m.addAttribute("message", message);
			return "veiculo/edit";
		}
		message = "Veiculo alterado com sucesso";
		return new ModelAndView("redirect:/", "message", message);
	}
}
