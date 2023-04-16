package com.ex.toll.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ex.toll.model.CalculateCostDistResponse;
import com.ex.toll.service.TollCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = {TollCalculatorController.class, TollCalculatorService.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class TollCalculatorControllerTest {

    @Autowired
    private MockMvc mvc;
	   
	   @Test
	   public void getProductsList() throws Exception {
		   ObjectMapper mapper = new ObjectMapper();

		   String uri = "/v1/calCostDist/QEW/Highway 400";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				   .accept(MediaType.APPLICATION_JSON_VALUE)
				   .content(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   CalculateCostDistResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(), CalculateCostDistResponse.class);
		   assertNotNull(response);
		   Assert.assertEquals(17.9795, response.getCost(), 0); 
		   Assert.assertEquals(71.918, response.getDistance(), 0); 
	   }
}
