import React from 'react';
import pic1 from '../assets/pic1.png';
import pic2 from '../assets/pic2.png';
import pic3 from '../assets/pic3.png';
import pic4 from '../assets/pic5.png';
import pic5 from '../assets/pic4.png';
import './services.css';

const Services = () => {
  return (
    <section className='service' id='services'>
      <h1>About Us</h1>
      <p className="about-intro">
        Discover your beauty companion! Our AI-powered platform helps you find makeup tailored to your skin tone and features — with smart recommendations, chatbot guidance, and seamless product exploration.
      </p>

      <div className='servicesbars'>

        <div className='servicesbar'>
          <img src={pic1} alt='Smart Diagnosis' className='serviceimg' />
          <div className='sertext'>
            <h2>Smart Skin Diagnosis</h2>
            <p>Describe your skin issues like dryness, acne, or sensitivity through a chatbot and get accurate product advice.</p>
          </div>
        </div>

        <div className='servicesbar'>
          <img src={pic2} alt='Tailored Picks' className='serviceimg' />
          <div className='sertext'>
            <h2>Tailored Product Picks</h2>
            <p>Based on your needs, we suggest foundations, concealers & skincare-friendly products — all matching your skin.</p>
          </div>
        </div>

        <div className='servicesbar'>
          <img src={pic3} alt='Chat Help' className='serviceimg' />
          <div className='sertext'>
            <h2>Instant Beauty Chat Help</h2>
            <p>Ask questions in real-time — our AI chatbot guides you with makeup tips and product suggestions that suit you.</p>
          </div>
        </div>

        <div className="servicesbar">
          <img src={pic5} alt='Buy Products' className='serviceimg' />
          <div className="sertext">
            <h2>Buy Makeup Products</h2>
            <p>Shop from all top brands — curated foundations, lipsticks & more matched to your skin profile.</p>
          </div>
        </div>

        <div className="servicesbar">
          <img src={pic4} alt='How It Works' className='serviceimg' />
          <div className="sertext">
            <h2>How It Works</h2>
            <p>We analyze your features and recommend ideal combinations using advanced AI algorithms.</p>
          </div>
        </div>

      </div>
    </section>
  );
};

export default Services;
