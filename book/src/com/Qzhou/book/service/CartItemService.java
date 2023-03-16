package com.Qzhou.book.service;

import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartIem(CartItem cartItem, Cart cart);
    //获取指定用户的所有购物车项列表 (需要设置book的详细信息)
    List<CartItem> getCartItemList(User user);
    //加载指定的购物车信息
    Cart getCart(User user);
}
