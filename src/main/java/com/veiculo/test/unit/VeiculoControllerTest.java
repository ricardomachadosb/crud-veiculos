package com.veiculo.test.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.veiculo.config.WebConfig;
import com.veiculo.controller.VeiculoController;
import com.veiculo.dao.impl.VeiculoDaoImpl;
import com.veiculo.entity.Veiculo;
import com.veiculo.service.VeiculoService;

/**
 * @author ricardo
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { WebConfig.class})
@WebAppConfiguration
public class VeiculoControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private VeiculoController veiculoController;
	
	@Mock
	private VeiculoService veiculoService;
	
	@Mock
	private VeiculoDaoImpl veiculoDaoImpl;
	 
	/**
	 * 
	 */
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception{
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		Veiculo veiculo1 = new Veiculo();
		Veiculo veiculo2 = new Veiculo();
		
		veiculos.add(veiculo1);
		veiculos.add(veiculo2);
		
		Mockito.when(veiculoService.list()).thenReturn(veiculos);
		
		mockMvc.perform(get("/veiculo/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("veiculo/list"))
		.andExpect(model().attribute("veiculos", veiculos));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void deleteTest() throws Exception  {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		Veiculo veiculo = new Veiculo();
		Veiculo veiculo2 = new Veiculo();
		
		veiculos.add(veiculo);
		veiculos.add(veiculo2);
		
		Mockito.when(veiculoService.list()).thenReturn(veiculos);
		
		Veiculo veiculo1 = new Veiculo();
		
		Mockito.doNothing().when(veiculoService).delete(veiculo1);
		Mockito.when(veiculoService.get(1)).thenReturn(veiculo1);
		
		mockMvc.perform(get("/veiculo/delete/1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(model().attribute("message", "Veiculo deletado com sucesso"));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void editTest() throws Exception{
		Veiculo veiculo1 = new Veiculo();
		
		Mockito.when(veiculoService.get(1)).thenReturn(veiculo1);
		
		mockMvc.perform(get("/veiculo/edit/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("veiculo/edit"));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void createTest() throws Exception{
		mockMvc.perform(get("/veiculo/create"))
		.andExpect(status().isOk())
		.andExpect(view().name("veiculo/create"));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void saveTest() throws Exception{
		Veiculo veiculo = new Veiculo();
		Mockito.doNothing().when(veiculoService).save(veiculo);
		
		mockMvc.perform(fileUpload("/veiculo/save").param("modelo", "m")
				.param("ano", "1111")
				.param("fabricante", "f"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"))
		.andExpect(model().attribute("message", "Veiculo salvo com sucesso"));
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void updateTest() throws Exception{
		Veiculo veiculo = new Veiculo();
		Mockito.when(veiculoService.get(1)).thenReturn(veiculo);
		
		Mockito.doNothing().when(veiculoDaoImpl).merge(veiculo);
		
		mockMvc.perform(fileUpload("/veiculo/update").param("modelo", "m")
				.param("ano", "1111")
				.param("fabricante", "f")
				.param("id", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"))
		.andExpect(model().attribute("message", "Veiculo alterado com sucesso"));
	}
	
}

