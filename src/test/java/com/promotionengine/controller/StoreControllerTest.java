package com.promotionengine.controller;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.CartItem;
import com.promotionengine.dto.SKUitem;
import com.promotionengine.exceptions.ArgumentException;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreControllerTest {
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
    public void getAllItems() throws Exception {
        Cart cart = new Cart();
        List<CartItem> items = new ArrayList<>();
        SKUitem skUitem = new SKUitem("A", 50);
        CartItem cartItem = new CartItem(skUitem, 50, false);
        items.add(cartItem);
        cart.addItem(skUitem);
        Mockito.when(iStore.getCart()).thenReturn(cart);
        mockMvc.perform(get("/store/items")).andExpect(status().isOk());
    }

    @Test
    public void getItem() throws ArgumentException, Exception {
        SKUitem skUitem = new SKUitem("A", 50);
        Mockito.when(iStore.getItem("A")).thenReturn(skUitem);
        mockMvc.perform(MockMvcRequestBuilders.post("/store/items/A")
                .content("A")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getCart() throws ArgumentException, Exception {
        Cart cart = new Cart();
        SKUitem skUitem = new SKUitem("A", 50);
        cart.addItem(skUitem);
        Mockito.when(iStore.getCart()).thenReturn(cart);
        mockMvc.perform(get("/store/cart")).andExpect(status().isOk());
    }

    @Test
    public void getCartTotal() throws ArgumentException, Exception {
        Cart cart = new Cart();
        SKUitem skUitem = new SKUitem("A", 50);
        cart.addItem(skUitem);
        Mockito.when(iStore.getCartTotal()).thenReturn(Double.valueOf("50"));
        mockMvc.perform(get("/store/cart/total")).andExpect(status().isOk());
    }

    @Test
    public void checkout() throws ArgumentException, Exception {
        Cart cart = new Cart();
        SKUitem skUitem = new SKUitem("A", 50);
        cart.addItem(skUitem);
        Mockito.when(iStore.checkout()).thenReturn(cart);
        mockMvc.perform(get("/store/cart/checkout")).andExpect(status().isOk());
    }
}
