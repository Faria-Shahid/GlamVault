import React from 'react'
import './herosection.css'
import Foundation2 from '../assets/Foundation2.png'
import lipstick2 from '../assets/lipstick (2).jpg'
import blush2 from '../assets/blush-stick2.png'
import Concealer2 from '../assets/Concealer2.png'
import Facepowder from '../assets/Facepowder1.png'
import Eyeliner2 from '../assets/eyeliner2.png'
import Mascara2 from '../assets/Mascara2.png'
import Highlighter from '../assets/Highlighter2.png'
import Eyeshadow2 from '../assets/Eyeshadow2.png'
const HeroSection = () => {
  return (
    <div id='hero' className='HeroSection-container'>
      <div  className='HeroSec-div'>
        <div className="img-herodiv">
        <img src={Foundation2} alt="" />
        </div>
        <h2  className='text-herodiv'>Foundation</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={lipstick2} alt="" />
        </div>
      <h2 className='text-herodiv'>Lipsticks</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={blush2} alt="" className='blush'/>
        </div>
      <h2 className='text-herodiv'>Blush</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Concealer2} alt="" />
        </div>
      <h2 className='text-herodiv'>Concealer</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Facepowder} alt="" />
        </div>
      <h2 className='text-herodiv'>Face Powders</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Eyeliner2} alt="" />
        </div>
      <h2 className='text-herodiv'>EyeLiners</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Mascara2} alt="" />
        </div>
      <h2 className='text-herodiv'>Mascaras</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Highlighter} alt="" />
        </div>
      <h2 className='text-herodiv'>Highlighter</h2>
      </div>
      <div className='HeroSec-div'>
      <div className="img-herodiv">
        <img src={Eyeshadow2} alt="" />
        </div>
      <h2 className='text-herodiv'>Eyeshadow</h2>
      </div>
    </div>
  )
}

export default HeroSection
