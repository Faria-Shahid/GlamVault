import React, { useContext, useState } from 'react';
import { CartContext } from './CartContext'; // Adjust path if needed

const Productlist = ({ id, image, name, price }) => {
  const { addToCart } = useContext(CartContext);

  const [quantity, setQuantity] = useState(1); // Track the quantity of the product

  const product = { id, image, name, price, quantity };

  const handleQuantityChange = (e) => {
    setQuantity(Math.max(0, e.target.value)); // Ensure quantity doesn't go below 0
  };

  return (
    <div className="product-card">
      <img src={image} alt={name} className="product-img" />
      <div className="product-info">
        <p className="product-name">{name}</p>
        <div className="quantity">
          <label>Quantity:</label>
          <input
            type="number"
            id={`quantity-${id}`}  // Corrected: use product ID for input ID
            min="0"
            value={quantity}  // Controlled input, use state value
            onChange={handleQuantityChange}  // Update state when quantity changes
            className="quantity-input"
          />
        </div>
        <div className="price-row">
          <span className="price">Rs.{price}</span>
        </div>
        <button
          onClick={() => addToCart({ ...product, quantity })}
          className="add-btn"
        >
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default Productlist;
