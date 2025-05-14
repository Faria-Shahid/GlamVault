import React, { useContext } from 'react';
import { FaTimes } from 'react-icons/fa'; // Import the cross icon
import { CartContext } from './CartContext';
import './cart.css';
// import Checkout from './Checkout';
import { Link as RouterLink } from 'react-router-dom'; 

const Cart = () => {
  const { cartItems, removeFromCart, toggleCartVisibility, isCartVisible } = useContext(CartContext);

  const handleCheckout = () => {
    // Here, you can handle the checkout process, like navigating to a checkout page or doing some action
     alert("Proceeding to checkout")
    // <Checkout/>
    toggleCartVisibility(); // Optionally close the cart after checkout
  };

  return (
    isCartVisible && (  // Only render the cart if isCartVisible is true
      <div className="cart">
        <div className="cart-header">
          <h2>Cart</h2>
          <button className="close-btn" onClick={toggleCartVisibility}>
            <FaTimes size={20} />
          </button>
        </div>

        {cartItems.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          <ul>
            {cartItems.map((item, index) => (
              <li key={index} className="cart-item">
                <img src={item.image} alt={item.name} width="40" />
                <span>{item.name}</span>
                <span>Rs.{item.price}</span>
                <span>{item.quantity}</span>

                <button onClick={() => removeFromCart(index)}>Remove</button>
              </li>
            ))}
           <RouterLink to="/checkout">
            <button className="btn-1 cart-btn" onClick={handleCheckout}>
              Check out
            </button></RouterLink>
          </ul>
        )}
      </div>
    )
  );
};

export default Cart;
