import React, { createContext, useState } from 'react';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);
  const [isCartVisible, setIsCartVisible] = useState(false); // Track visibility of the cart

  const addToCart = (product) => {
    setCartItems(prev => [...prev, product]);
  };

  const removeFromCart = (index) => {
    const updatedCart = [...cartItems];
    updatedCart.splice(index, 1);
    setCartItems(updatedCart);
  };

  const toggleCartVisibility = () => {
    setIsCartVisible(prev => !prev); // Toggle cart visibility
  };

  return (
    <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, toggleCartVisibility, isCartVisible }}>
      {children}
    </CartContext.Provider>
  );
};
