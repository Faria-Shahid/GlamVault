import { useForm } from 'react-hook-form';
import './checkout.css';
import React, { createContext, useState, useContext ,useEffect } from 'react';
import { CartContext } from './CartContext';
import { Link } from 'react-router-dom'; 
const Checkout = () => {
  const { cartItems, removeFromCart } = useContext(CartContext);
  const [totalAmount, setTotalAmount] = useState(0);
  const shippingFee = 250;
  const [orderCompleted, setOrderCompleted] = useState(false);

  const calculateTotalAmount = () => {
    const total = cartItems.reduce((total, item) => {
      const cleanedPrice = parseFloat(String(item.price).replace(/[^0-9.]/g, '')) || 0;
      const quantity = Number(item.quantity) || 0;
      return total + cleanedPrice * quantity;
    }, 0);
    setTotalAmount(total);
  };

  // useEffect(() => {
  //   calculateTotalAmount();
  // }, [cartItems]);
  
  // Function to remove an item from the cart
  

  // Function to calculate total amount
  // const calculateTotalAmount = () => {
  //   const total = cartItems.reduce((total, item) => {
  //     const cleanedPrice = parseFloat(String(item.price).replace(/[^0-9.]/g, '')) || 0; // Clean the price
  //     const quantity = Number(item.quantity) || 0; // Ensure quantity is a number
  //     return total + cleanedPrice * quantity;
  //   }, 0);
  //   setTotalAmount(total); // Update total amount
  // };

  // Recalculate the total whenever cartItems change
  useEffect(() => {
    calculateTotalAmount();
  }, [cartItems]);

  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm();

  const onSubmit = (data) => {
    console.log('Form Data:', data);
    
  }

  return (
    <div id="checkout" className="checkout-wrapper">
      <form className="checkout-left-form" onSubmit={handleSubmit(onSubmit)}>
        {/* Contact Section */}
        <h2 className="checkout-section-title">Contact</h2>
        {/* Email */}
        <div className="checkout-fullrow">
          <input
            type="email"
            placeholder="Email"
            className="checkout-input"
            {...register('email', {
              required: 'Email is required',
              pattern: {
                value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                message: 'Enter a valid email address'
              }
            })}
          />
          {errors.email && <p className="checkout-error">{errors.email.message}</p>}
        </div>

        {/* Delivery Section */}
        <h2 className="checkout-section-title">Delivery</h2>
        {/* Country */}
        <div className="checkout-fullrow">
          <select className="checkout-select" {...register('country')}>
            <option value="Pakistan">Pakistan</option>
          </select>
        </div>

        {/* Name Fields */}
        <div className="checkout-name-row">
          <input
            type="text"
            placeholder="First name"
            className="checkout-input"
            {...register('firstName', {
              pattern: {
                value: /^[A-Za-z]+$/,
                message: 'First name should contain only letters'
              }
            })}
          />
          <input
            type="text"
            placeholder="Last name"
            className="checkout-input"
            {...register('lastName', {
              required: 'Last name is required',
              pattern: {
                value: /^[A-Za-z]+$/,
                message: 'Last name should contain only letters'
              }
            })}
          />
        </div>
        {errors.firstName && <p className="checkout-error">{errors.firstName.message}</p>}
        {errors.lastName && <p className="checkout-error">{errors.lastName.message}</p>}

        {/* Address */}
        <div className="checkout-fullrow">
          <input
            type="text"
            placeholder="Address"
            className="checkout-input"
            {...register('address', { required: 'Address is required' })}
          />
          {errors.address && <p className="checkout-error">{errors.address.message}</p>}
        </div>

        {/* City and Postal Code */}
        <div className="checkout-city-row">
          <select {...register('city')} className="checkout-select">
            <option value="Karachi">Karachi</option>
          </select>
          <input
            type="text"
            placeholder="Postal code (optional)"
            className="checkout-input"
            {...register('postalCode')}
          />
        </div>

        {/* Phone */}
        <div className="checkout-fullrow">
          <input
            type="text"
            placeholder="Phone"
            className="checkout-input"
            {...register('phone', {
              required: 'Phone number is required',
              pattern: {
                value: /^[0-9]+$/,
                message: 'Phone number must contain only digits'
              }
            })}
          />
          {errors.phone && <p className="checkout-error">{errors.phone.message}</p>}
        </div>

        {/* Shipping Method */}
        <h2 className="checkout-section-title">Shipping Method</h2>
        <div className="checkout-option-box checkout-selected-option">
          <span>Expedited Shipping</span>
          <span className="checkout-price">Rs 250.00</span>
        </div>

        {/* Payment Section */}
        <h2 className="checkout-section-title">Payment</h2>
        <p className="checkout-secure-text">All transactions are secure and encrypted.</p>
        <div className="checkout-option-box checkout-selected-option">
          <span>Cash on Delivery (COD)</span>
        </div>

        <div className="checkout-right">
          <h2 className="checkout-section-title">Order Summary</h2>
          {cartItems.length === 0 ? (
            <p>Your cart is empty.</p>
          ) : (
            <div className="order-summary">
              {cartItems.map((item, index) => {
                const cleanedPrice = parseFloat(String(item.price).replace(/[^0-9.]/g, '')) || 0;
                const totalPrice = cleanedPrice * item.quantity;

                return (
                  <div key={index} className="summary-item">
                    <img src={item.image} alt={item.name} />
                    <div className="summary-item-details">
                      <div>{item.name} (x{item.quantity})</div>
                      <div>Rs. {cleanedPrice}</div>
                      <span>Rs. {totalPrice}</span>
                    </div>
                    <button
                      type="button"
                      onClick={() => removeFromCart(index)}
                      style={{
                        padding: '5px 10px',
                        backgroundColor: 'red',
                        color: 'white',
                        border: 'none',
                        borderRadius: '5px',
                        cursor: 'pointer'
                      }}
                    >
                      Remove
                    </button>
                  </div>
                );
              })}
              <hr />
              <div className="summary-total">
                <strong>Total (Items):</strong>
                <strong>Rs. {totalAmount}</strong>
              </div>
              <div className="summary-total">
                <strong>Shipping:</strong>
                <strong>Rs. {shippingFee}</strong>
              </div>
              <div className="summary-total">
                <strong>Total (With Shipping):</strong>
                <strong>Rs. {totalAmount + shippingFee}</strong>
              </div>
            </div>
          )}
        </div>

        <button
          type="button"
          className="checkout-complete-order-btn"
        >
         <Link to="/OrderRecieved" className="Order-Recieved">Complete Order</Link>
        </button>
      </form>
    </div>
  );
};
export default Checkout;
