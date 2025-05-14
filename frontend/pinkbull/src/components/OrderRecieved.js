import React from 'react';
import './OrderRecieved.css';
import { Link } from 'react-router-dom'; 
const OrderRecieved = () => {
  return (
    <div id="order-received-wrapper" className="order-received-container">
      <h2 className="order-received-title"> Order Received Successfully!</h2>
      <p className="order-received-message">
        Thank you for your purchase. Our team will contact you shortly to confirm your order.
        <br></br>
        In case of any query contact us at <span className='gmail-id'>info@pinkbull.gmail.com</span>
      </p>
      <p className="order-received-delivery">
        <strong>Expected Delivery:</strong> Within 15 days
      </p>
      <button className='Returnhome'> <Link to="/Home" className="Return-Homee">Back to Home</Link></button>
    </div>
  );
};

export default OrderRecieved;
