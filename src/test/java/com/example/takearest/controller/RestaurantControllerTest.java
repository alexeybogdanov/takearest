package com.example.takearest.controller;

import com.example.takearest.TakearestApplication;
import com.example.takearest.entity.Restaurant;
import com.example.takearest.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TakearestApplication.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class RestaurantControllerTest {

    protected MockMvc mvc;

    @MockBean
    private RestaurantRepository repository;

    @Autowired
    WebApplicationContext webApplicationContext;

    Restaurant restaurant;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        restaurant = new Restaurant();
        restaurant.setName("TestName");
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    public void find() throws Exception {

        given(repository.findById(1L)).willReturn(Optional.of(restaurant));

        mvc.perform(get("/api/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("TestName")));
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    public void findByName() throws Exception {
        given(repository.findByName("TestName")).willReturn(Optional.of(restaurant));

        mvc.perform(get("/api/restaurants/search/by-name?name=TestName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("TestName")));
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    public void delete() throws Exception {
        given(repository.findById(2L)).willReturn(Optional.of(restaurant));
        doNothing().when(repository).deleteById(1L);
        mvc.perform(MockMvcRequestBuilders.delete("/api/restaurants/{id}", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}