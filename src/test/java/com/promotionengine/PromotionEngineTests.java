package com.promotionengine;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.SKUitem;
import com.promotionengine.rule.CombinedItemFixedPricePromotion;
import com.promotionengine.rule.NItemForFixedPricePromotion;
import com.promotionengine.rule.PromotionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionEngineTests {
    SKUitem a;
    SKUitem b;
    SKUitem c;
    NItemForFixedPricePromotion pr1;
    NItemForFixedPricePromotion pr2;
    SKUitem d;
    CombinedItemFixedPricePromotion pr3;

    @Before
    public void setup() {
        a = new SKUitem("A", 50);
        b = new SKUitem("B", 30);
        c = new SKUitem("C", 20);
        d = new SKUitem("D", 15);
        pr1 = new NItemForFixedPricePromotion("A", 3, 130);
        pr2 = new NItemForFixedPricePromotion("B", 2, 45);
        pr3 = new CombinedItemFixedPricePromotion(Arrays.asList("C", "D"), 30);
    }

    @Test
    public void testScenarioA() {
        Cart cart = new Cart();
        cart.addItem(a);
        cart.addItem(b);
        cart.addItem(c);
        List<PromotionRule> promotions = Arrays.asList(new NItemForFixedPricePromotion[]{pr1, pr2});
        Assert.assertEquals(3, cart.items.size());
        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(100)));
        applyPromotionsOnCart(promotions, cart);
        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(100)));
    }

    @Test
    public void testScenarioB() {
        Cart cart = new Cart();
        cart.addItem(a, 5);
        cart.addItem(b, 5);
        cart.addItem(c);
        Assert.assertEquals(11, cart.items.size());
        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(420)));
        List<PromotionRule> promotions = Arrays.asList(new NItemForFixedPricePromotion[]{pr1, pr2});
        applyPromotionsOnCart(promotions, cart);

        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(370)));
    }

    @Test
    public void testScenarioC() {
        Cart cart = new Cart();
        cart.addItem(a, 3);
        cart.addItem(b, 5);
        cart.addItem(c);
        cart.addItem(d);

        Assert.assertEquals(10, cart.items.size());
        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(335)));
        List<PromotionRule> promotions1 = Arrays.asList(new CombinedItemFixedPricePromotion[]{pr3});
        List<PromotionRule> promotions = Arrays.asList(new NItemForFixedPricePromotion[]{pr1, pr2});
        applyPromotionsOnCart(promotions1, cart);
        applyPromotionsOnCart(promotions, cart);
        Assert.assertEquals(Optional.of(cart.totalPrice()), Optional.of(Double.valueOf(280)));
    }

    private void applyPromotionsOnCart(List<PromotionRule> promotions, Cart cart) {
        promotions.forEach(p -> {
            if (p.isApplicable(cart)) {
                p.execute(cart);
            }
        });
    }

}
