package com.javaexpress.service;


import com.javaexpress.exceptions.ResourceNotFoundException;
import com.javaexpress.models.CartItems;
import com.javaexpress.models.Product;
import com.javaexpress.models.ShoppingCart;
import com.javaexpress.models.User;
import com.javaexpress.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;
    public ShoppingCart createShoppingCart(Long userId) {
        ShoppingCart cart=new ShoppingCart();
        User user=new User();
        user.setId(userId);
        cart.setId(userId); //Associate userId with shopping cart
      return  shoppingCartRepository.save(cart);
    }

    public ShoppingCart retrieveShoppingCartById(Long cartId) {
       return shoppingCartRepository.findById(cartId)
                .orElseThrow(()-> new ResourceNotFoundException("Shopping Cart is not found" +cartId));
    }

    public Product fetchProductById(Long productId) {
        return productService.getByProductId(productId);

    }

    public void addToCart(ShoppingCart shoppingCart, Product product, int quantity, User user) {
        CartItems item=findCartItemInCart(shoppingCart,product);
        if(item!=null){
            item.setQuantity(item.getQuantity() + quantity);

        }else {
            item=createNewCartItem(shoppingCart,product,quantity,user);
            shoppingCart.getCartItemsList().add(item);

        }
        shoppingCartRepository.save(shoppingCart);
    }

    private CartItems createNewCartItem(ShoppingCart shoppingCart, Product product, int quantity, User user) {
        CartItems cartItems=new CartItems();
        cartItems.setShoppingCart(shoppingCart);
        cartItems.setProduct(product);
        cartItems.setQuantity(quantity);
        cartItems.setUser(user);
        return cartItems;

    }

    private CartItems findCartItemInCart(ShoppingCart shoppingCart, Product product) {
       return shoppingCart.getCartItemsList().stream()
                .filter(item->item.getProduct().equals(product))
                .findFirst().orElse(null);
    }
}
