package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.CartItemDAO;
import com.Qzhou.book.pojo.Book;
import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.BookService;
import com.Qzhou.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartIem(CartItem cartItem, Cart cart) {
        //判断当前用户的购物车中是否有这本书的CartItem 有则加1 无则添加
        //1、如果当前用户的购物车已存在这个图书 则数量加一
        //2、否则，在我的购物车中新增一个这本图书的CartItem 数量是1
        if(cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap=new HashMap<>();
            }
            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                updateCartItem(cartItemTemp);
            }else{
                addCartItem(cartItem);
            }
        }else { //没有购物车的情况
            addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        //此时 查询的是 cartItem （其中只有book id） 需要book 所有信息
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for(CartItem cartItem:cartItemList){
           Book book= bookService.getBookById(cartItem.getBook().getId());
           cartItem.setBook(book);
           //此处调用getXj() 目的是计算出 Xj 的值赋给Xj
           cartItem.getXj();
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        //此时cartItemList 中book就有值了
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer,CartItem> cartItemMap=new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }
}
