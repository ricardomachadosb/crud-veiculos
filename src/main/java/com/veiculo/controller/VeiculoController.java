package com.veiculo.controller;

import java.util.List;

import javax.servlet.ServletContext;

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
import com.veiculo.enums.RequestStatus;
import com.veiculo.exception.VeiculoException;
import com.veiculo.service.FileService;
import com.veiculo.service.VeiculoService;
import com.veiculo.utils.HashMapUtils;

/**
 * @author ricardo
 *
 */
@Controller
public class VeiculoController {
	
	@Autowired
	VeiculoService veiculoService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	ServletContext servletContext;
	
	/**
	 * @param m
	 * @param message
	 * @return
	 */
	@RequestMapping(value={"veiculo/list", "/"})
	public String list(Model m, @RequestParam(required = false) String message,
			@RequestParam(required = false) RequestStatus requestStatus){
		List<Veiculo> veiculos = veiculoService.list();
		m.addAttribute("veiculos", veiculos);
		
		if(message == null){
			message = "";
		}
		
		if(requestStatus == null){
			requestStatus = RequestStatus.SUCCESS;
		}
		
		m.addAttribute("requestStatus", requestStatus);
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
		RequestStatus requestStatus = null;
		
		try {
			veiculo = veiculoService.get(id);
			veiculoService.delete(veiculo);
		}catch(VeiculoException e){
			message = e.getMessage();
			requestStatus = RequestStatus.ERROR;
		}
		
		if(message.length() < 1){
			message = "Veiculo deletado com sucesso";
			requestStatus = RequestStatus.SUCCESS;
		}
		
		HashMapUtils model = new HashMapUtils(message, requestStatus);
		
		return new ModelAndView("redirect:/", model);
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
		Veiculo veiculo = new Veiculo(ano, fabricante, modelo, (foto != null) ? foto.getOriginalFilename() : null);
		
		
		try{
			veiculoService.save(veiculo);
		}catch(Exception e){
			message = "Problemas ao criar veículo, verifique os valores informados e tente novamente";
		}
		
		if(foto != null && foto.getOriginalFilename().length() > 0){
			try{
				String filePath = servletContext.getRealPath("/resources/") + "/images/" + veiculo.getId() + foto.getOriginalFilename();
				fileService.saveImage(foto, filePath);
			}catch(Exception e){
				message = "Problemas ao salvar o arquivo";
			}
		}
		
		if(message.length() > 0){
			m.addAttribute("veiculo", veiculo);
			m.addAttribute("message", message);
			m.addAttribute("requestStatus", RequestStatus.ERROR);
			return "veiculo/create";
		}
		
		HashMapUtils model = new HashMapUtils("Veículo criado com sucesso", RequestStatus.SUCCESS);
		
		return new ModelAndView("redirect:/", model);
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
		}catch(Exception e){
			message = "Problemas ao buscar veículo, atualize a pagina e tente novamente";
		}
		
		if(message.length() > 0){
			HashMapUtils model = new HashMapUtils(message, RequestStatus.ERROR);
			return new ModelAndView("redirect:/", model);
		}
		
		m.addAttribute("veiculo", veiculo);
		m.addAttribute("requestStatus", RequestStatus.SUCCESS);
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
			if(foto != null && foto.getOriginalFilename().length() > 0){
				veiculo.setFoto(foto.getOriginalFilename());
				String filePath = servletContext.getRealPath("/resources/") + "/images/" + veiculo.getId() + foto.getOriginalFilename();
				fileService.saveImage(foto, filePath);
			}
		}catch(Exception e){
			message = "Problemas ao alterar veículo, verifique os valores informado e tente novamente";
		}
		
		if(message.length() > 0){
			m.addAttribute("veiculo", veiculo);
			m.addAttribute("message", message);
			m.addAttribute("requestStatus", RequestStatus.SUCCESS);
			return "veiculo/edit";
		}
		
		message = "Veículo alterado com sucesso";
		
		HashMapUtils model = new HashMapUtils(message, RequestStatus.SUCCESS);
		return new ModelAndView("redirect:/", model);
	}
}
