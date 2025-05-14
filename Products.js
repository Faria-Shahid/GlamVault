import React from 'react'
import Productlist from './Productlist'
import './product.css'
import foundation from '../assets/foundation.jpg';
import concealer from '../assets/concealer.jpg';
import lipstick from '../assets/lipstick (2).jpg';
import mattegloss from '../assets/mattegloss.jpg';
import mascara from '../assets/mascara.jpg';
import eyeliner from '../assets/eyeliner.jpg';
import gloss from '../assets/gloss.jpg';
import blush from '../assets/blush.jpg';

const products = [
    {
      image: foundation,
      name: 'Matte Tinted Foundation for Oily Skin',
      price: '3,739.15',
      oldPrice: '4,399.00',
      discount: '15%',
    },
    {
      image: foundation,
      name: 'Superdewy Tinted Foundation for All Skin Types',
      price: '2,932.50',
      oldPrice: '3,450.00',
      discount: '15%',
    },
    {
      image: concealer,
      name: 'Pinkbull concealer with hyderating effect',
      price: '2,932.50',
      oldPrice: '3,450.00',
      discount: '15%',
    },
    {
      image: lipstick,
      name: 'Pinkbull Lipstick - Rose Lustre',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
    {
      image: blush,
      name: 'Beam Bright Blush - Rose Bloom',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
    {
      image: mattegloss,
      name: 'Matte Lip Gloss - Coral Nude',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
    {
      image: gloss,
      name: 'Hydrating Gloss - Glossy Pink',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
    {
      image: mascara,
      name: 'Volume Mascara - Jet Black',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
    {
      image: eyeliner,
      name: 'Precision Eyeliner - Bold Black',
      price: '3,144.15',
      oldPrice: '3,699.00',
      discount: '15%',
    },
  ];
  




const Products = () => {
  return (
    <div className="product-list" id='product'>
      {products.map((item, index) => (
        <Productlist key={index} {...item} />
      ))}
    </div>
  )
}

export default Products