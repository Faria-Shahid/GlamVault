import React from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import Header from './components/Header';
import Home from './components/Home';
import Services from './components/Services';
import Chatbot from './components/Chatbot';
import Products from './components/Products';
import { CartProvider } from './components/CartContext';
import Cart from './components/Cart';
import OrderRecieved from './components/OrderRecieved';
// import About from './components/About';
import Signup from './components/SignupPage'
import Contact from './components/ContactUs';
import PrivacyPolicy from './components/PrivacyPolicy';
import Footer from './components/Footer';
import Login from './components/LoginPage';
import Foundation from './components/Foundation';
import Concealer from './components/Concealer';
import Lipstick from './components/Lipstick';
import Eyeshadow from './components/Eyeshadow';
import Eyeliner from './components/Eyeliner';
import Blush from './components/Blush';
import Checkout from './components/Checkout';
import HeroSection from './components/Herosection';
import Featurs from './components/Featurs';


function App() {
  const location = useLocation();
  const noHeaderRoutes = ['/checkout']; // list routes without Header

  return (
    <CartProvider>
      <div className="App">
        {!noHeaderRoutes.includes(location.pathname) && <Header />}

        <Routes>
          <Route path="/" element={
            <>
            <HeroSection/>
              <Home />
              <Services />
              {/* <About /> */}
              <Contact />
              <Featurs/>
              <Footer />
            </>
          } />
          <Route path="/" element={<Home />} />
          <Route path="/hero" element={<HeroSection/>}/>
          <Route path="/services" element={<Services />} />
          {/* <Route path="/about" element={<About />} /> */}
          <Route path="/contact" element={<Contact />} />
          <Route path="/product" element={<Products />} />
          <Route path="/product/foundation" element={<Foundation />} />
          <Route path="/product/concealer" element={<Concealer />} />
          <Route path="/product/lipstick" element={<Lipstick />} />
          <Route path="/product/eyeshadow" element={<Eyeshadow />} />
          <Route path="/product/eyeliner" element={<Eyeliner />} />
          <Route path="/product/blush" element={<Blush />} />
           <Route path="/Home" element={<Home />} />
          <Route path="/chatbot" element={<Chatbot />} />
            <Route path="/Signup" element={<Signup />} />
              <Route path="/Login" element={<Login />} />
          <Route path="/checkout" element={<Checkout />} />
            <Route path="/privacy-policy" element={<PrivacyPolicy />} />
             <Route path="/ContactUs" element={<Contact />} />
              <Route path="/AboutUs" element={<Services />} />
               <Route path="/chat" element={<Chatbot />} />
               <Route path="/OrderRecieved" element={<OrderRecieved />} />
        </Routes>
      </div>

      <Cart />
    </CartProvider>
  );
}

export default App;
