import React, { useContext, useEffect, useState } from 'react';
import './header.css';
import { Link as RouterLink } from 'react-router-dom'; 
import { FaShoppingCart } from 'react-icons/fa';
import { CartContext } from './CartContext';
import Cart from './Cart';

function Header() {
  const { cartItems, toggleCartVisibility, isCartVisible } = useContext(CartContext);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const loginStatus = localStorage.getItem('isLoggedIn') === 'true';
    setIsLoggedIn(loginStatus);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
    window.location.href = '/'; // Optional: reload or redirect
  };

  return (
    <nav className='navbar'>
      <h1>PinkBull.</h1>
      <div className="navbarlist">
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/services">About</RouterLink>

        <div className="dropdown">
          <span className="dropdown-title">Products</span>
          <div className="dropdown-content">
            <RouterLink to="/product">All</RouterLink>
            <RouterLink to="/product/foundation">Foundation</RouterLink>
            <RouterLink to="/product/concealer">Concealer</RouterLink>
            <RouterLink to="/product/lipstick">Lipsticks</RouterLink>
            <RouterLink to="/product/Eyeshadow">Eyeshadow</RouterLink>
            <RouterLink to="/product/Eyeliner">Eyeliner</RouterLink>
            <RouterLink to="/product/Blush">Blush</RouterLink>
          </div>
        </div>

        <RouterLink to="/Contact">Contact</RouterLink>
      </div>

      <div className="right-icons">
        {!isLoggedIn ? (
          <RouterLink to="/Signup">
            <button className='btnSignupNavbar'>Signup/Login</button>
          </RouterLink>
        ) : (
          <button onClick={handleLogout} className='btnSignupNavbar'>Logout</button>
        )}

        <RouterLink to="/chatbot">
          <button className='btn-1'>Chat with us</button>
        </RouterLink>

        <div className="cart-icon" onClick={toggleCartVisibility}>
          <FaShoppingCart size={22} />
          {cartItems.length > 0 && (
            <span className="cart-badge">{cartItems.length}</span>
          )}
        </div>
      </div>

      {isCartVisible && <Cart />}
    </nav>
  );
}

export default Header;
