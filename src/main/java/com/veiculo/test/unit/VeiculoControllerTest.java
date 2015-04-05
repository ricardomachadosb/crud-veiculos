package com.veiculo.test.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.veiculo.config.WebConfig;
import com.veiculo.controller.VeiculoController;
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
	 
	
	@Resource
	private WebApplicationContext webApplicationContext;
	/**
	 * 
	 */
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
		MockitoAnnotations.initMocks(this);
	}
	
   @Configuration
    public static class TestConfiguration {
        @Bean
        public MultipartResolver multipartResolver() {
    		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    		multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
            return Mockito.spy(multipartResolver);
        }
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
		
		mockMvc.perform(post("/veiculo/save").contentType(MediaType.MULTIPART_FORM_DATA).param("modelo", "m")
				.param("ano", "1111")
				.param("fabricante", "f"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("veiculo/list"))
		.andExpect(model().attribute("message", "Veiculo salvo com sucesso"));
	}
	
}
