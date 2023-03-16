package com.Qzhou.book.controller;

import com.Qzhou.book.pojo.Book;
import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书加到购物车
        cartItemService.addOrUpdateCartIem(cartItem,user.getCart());
        return "redirect:cart.do";
    }
    //加载当前购物车信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }
    //购物车数量 + -
    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        //调用Cart 中的三个属性get方法，目的是计算此处三个属性的值，否则三个属性都为null
        //导致在 gson 转化时会被忽略
        cart.gettotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr;

    }
}
