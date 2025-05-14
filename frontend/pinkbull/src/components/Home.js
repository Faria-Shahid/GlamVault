import React from 'react'

import homeimage from '../assets/intro-pic.png';
// import homeimage2 from '../assets/homeimg2.png';
import { Link } from 'react-router-dom'; 
import './home.css'


function Home() {
  return (
    <div className='home-sec' id='home'>
        
        <div className='intro-container'>
           <h1> Welcome to <span>PinkBull </span>– Your Smart Makeup Stylist!</h1>
           <h4>Discover personalized beauty with AI.</h4>
           <p>Our advanced AI-powered system analyzes your features, preferences, 
            and skin tone to recommend the perfect makeup products and looks – just for you.
             Whether you're going for a natural glow or a bold transformation, 
             PinkBull tailors beauty suggestions that fit you.</p>
             <button className='btn-1'> <Link to="/product" className="Explore-btn">Explore</Link></button>
        </div>
       
        <div className='image-container'>
        <img className='makeupimg' src={homeimage} alt='Makeup_intro' />
       
        </div>
    
    </div>
    
  )
}

export default Home