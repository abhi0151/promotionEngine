package com.promotionengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promotionengine.dto.SKUitemDTO;
import com.promotionengine.store.IStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SKUItemControllerTest {

    @MockBean
    IStore iStore;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllSKUItems() throws Exception {
        mockMvc.perform(get("/skuItems/all")).andExpect(status().isOk());
    }

    @Test
    public void getSKUItem() throws Exception {
        mockMvc.perform(get("/skuItems/A")).andExpect(status().isOk());
    }

    @Test
    public void addSKUItem() throws Exception {

        SKUitemDTO skUitemDTO = new SKUitemDTO("A",50);

        Mockito.when(iStore.addSKUitem(Mockito.any(SKUitemDTO.class))).thenReturn(skUitemDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/skuItems/add")
                        .content(asJsonString(skUitemDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
