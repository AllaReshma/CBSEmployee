package com.pack.cbs.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.pack.cbs.model.Cart;
@RunWith(SpringRunner.class)
@DataJpaTest
class CartRepositoryTest {
	@Autowired
    private CartRepository cartrepository;

    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void testNewCart() throws Exception{
        Cart cart = getCart();
        Cart saveInDb = testEntityManager.persist(cart);
        Cart getFromInDb = cartrepository.findById(saveInDb.getProductId()).get();
        assertThat(getFromInDb).isEqualTo(saveInDb);
    }
	private Cart getCart() {
		Cart ecart=new Cart();
		ecart.setProductName("Kurthis");
		ecart.setProductPrice(1000);
		ecart.setQuantity(1);
		return ecart;
	}
	@Test
    public void testGetProductById() throws Exception{
        Cart cart = new Cart();
        cart.setProductName("Water Bottle");
		cart.setProductPrice(1500);
	
        Cart saveInDb = testEntityManager.persist(cart);

        Cart getInDb = cartrepository.findById(cart.getProductId()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
	}
	 @Test
	    public void testGetAllProducts() throws Exception{
		 Cart cart = new Cart();
		 cart.setProductName("Sandals");
			cart.setProductPrice(100);
			cart.setQuantity(2);
	        
	        Cart ecart = new Cart();
	        ecart.setProductName("Shoes");
			ecart.setProductPrice(1000);
			ecart.setQuantity(2);
	        testEntityManager.persist(cart); 
	        testEntityManager.persist(ecart);

	        List<Cart> cartList = (List<Cart>) cartrepository.findAll();

	        Assert.assertEquals(2, cartList.size());
	    }
	 @Test
	    public void testDeleteCartById() throws Exception{
		 Cart cart = new Cart();
		    cart.setProductName("Samsung");
			cart.setProductPrice(60000);
			cart.setQuantity(1);
	        

	        Cart cart1 = new Cart();
	        cart1.setProductName("Nokia");
			cart1.setProductPrice(40000);
			cart1.setQuantity(1);
	        

	        Cart cart2 = testEntityManager.persist(cart);
	        testEntityManager.persist(cart1);

	        testEntityManager.remove(cart2);

	        List<Cart> ecart = (List<Cart>) cartrepository.findAll();
	        Assert.assertEquals(ecart.size(), 1);

	    }
	 @Test
	    public void testUpdateCart(){

		 Cart cart = new Cart();
		 cart.setProductName("Shirt");
			cart.setProductPrice(1600);
			cart.setQuantity(1);
	        
	        testEntityManager.persist(cart);

	        Cart getFromDb = cartrepository.findById(cart.getProductId()).get();
	        getFromDb.setProductPrice(1500);
	        testEntityManager.persist(getFromDb);

	        assertThat(getFromDb.getProductPrice()).isEqualTo(1500);
	    }


}
