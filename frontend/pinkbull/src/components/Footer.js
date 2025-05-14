import React from 'react';
import './footer.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInstagram, faFacebookF, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { Link } from 'react-router-dom'; // ← add this import

const Footer = () => {
  return (
    <div className="footer">
      <div className='Footer-container'> 
        <div className="Footer-help-section">
          <h3>Get Help</h3>
          <ul className="Footer-help-links">
            <li>  <Link to="/ABoutUs" className="Footer-link">About Us</Link></li>
            <li>   <Link to="/Chat" className="Footer-link">Chat Us</Link></li>
            <li>
               <Link to="/ContactUs" className="Footer-link">Contact Us</Link>
            </li>
            <li>
              <Link to="/privacy-policy" className="Footer-link">Privacy Policy</Link>
            </li>
          </ul>
        </div>
      <div className="Footer-shop-section">
        <h3>Pink Bull Offerings</h3>
        <ul className="Footer-shop-links">
          <li>Home</li>
          <li>Shop All Brands</li>
          <li>Makeup</li>
          <li>Our Services</li>
        </ul>
      </div>

      <div className="Footer-newsletter-section">
        <h3 className='Newsletter-heading'>Sign up for emails to receive latest news from us:</h3> 
        <form className="Footer-form">
          <input
            type="email"
            placeholder="Enter your Email"
            className="Footer-input-email"
          />
          <button type="submit" className="Footer-submit-btn">Submit</button>
        </form>
        <div className="Footer-social-icons">
  <FontAwesomeIcon icon={faInstagram} className="Footer-icon" />
  <FontAwesomeIcon icon={faFacebookF} className="Footer-icon" />
  <FontAwesomeIcon icon={faTwitter} className="Footer-icon" />
</div>


      </div>
    </div>
    <div className="Footer-Copyrightstag">
    © 2025 , All rights reserved by Pink Bull
    </div>
    </div>
  );
};

export default Footer;
