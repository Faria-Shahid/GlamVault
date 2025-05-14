import React from 'react'
import './feature.css'
import { GiPlantRoots } from 'react-icons/gi';
import { AiFillStar } from 'react-icons/ai';
import { BiSupport } from 'react-icons/bi';
import { FaTags } from 'react-icons/fa';
import { FaShippingFast } from 'react-icons/fa';
const Featurs = () => {
  return (
    <div><div className="features-section">
    <div className="feature-card">
      <GiPlantRoots className="feature-icon" />
      <h4>All natural</h4>
      <p>Our products are free from harsh chemicals and synthetic fragrances</p>
    </div>
    <div className="feature-card">
      <AiFillStar className="feature-icon" />
      <h4>Top quality</h4>
      <p>We offer only the best ingredients and trusted formulas</p>
    </div>
    <div className="feature-card">
      <BiSupport className="feature-icon" />
      <h4>24/7 support</h4>
      <p>We’re here for you anytime through chat or email</p>
    </div>
    <div className="feature-card">
      <FaTags className="feature-icon" />
      <h4>Attractive offer</h4>
      <p>Save more with amazing deals and seasonal discounts</p>
    </div>
    <div className="feature-card">
      <FaShippingFast className="feature-icon" />
      <h4>Fast shipping</h4>
      <p>Fast shipment right to your doorstep</p>
    </div>
  </div></div>
  )
}

export default Featurs