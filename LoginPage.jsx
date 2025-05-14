import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import axios from './axiosConfig'; // ✅ use your custom axios instance
import './LoginPage.css';
import Loginimg from './makeup3.png';

const LoginPage = () => {
  const { register, handleSubmit, formState: { errors }, reset } = useForm();
  const [loginError, setLoginError] = useState('');
  const [loginSuccess, setLoginSuccess] = useState('');

  const onSubmit = async (data) => {
    const { email, password } = data;

    // Demo credentials bypass
    if (email === 'demo@gmail.com' && password === 'demo123') {
      setLoginSuccess('Login successful!');
      setLoginError('');
      localStorage.setItem('isLoggedIn', 'true');
      reset();
      setTimeout(() => {
        window.location.href = '/';
      }, 1000);
      return;
    }

    try {
      const response = await axios.post('/api/auth/login', data);

      if (response.data.token) {
        setLoginSuccess('Login successful!');
        setLoginError('');
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('token', response.data.token); // ✅ store the token
        reset();
        setTimeout(() => {
          window.location.href = '/';
        }, 1000);
      } else {
        setLoginError('Invalid email or password');
        setLoginSuccess('');
      }
    } catch (error) {
      console.error('Login failed:', error);
      setLoginError('Server error or invalid credentials');
      setLoginSuccess('');
    }
  };

  return (
    <div className='LoginContainer'>
      <div className="LoginWrapper">
        <div className="LoginFormBox">
          <h2 className='Login-heading'>Login to your Account</h2>
          <form onSubmit={handleSubmit(onSubmit)} className="login-form">
            <div className='LP-div Email-div-Loginpg'>
              <label htmlFor="login-email">Email:</label>
              <input
                id="login-email"
                type="email"
                placeholder="Enter your email"
                {...register("email", {
                  required: "Email is required",
                  pattern: {
                    value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                    message: "Enter a valid email"
                  }
                })}
              />
              {errors.email && <p className="error-login">{errors.email.message}</p>}
            </div>

            <div className='LP-div Password-div-Loginpg'>
              <label htmlFor="login-password">Password:</label>
              <input
                id="login-password"
                type="password"
                placeholder="Enter your password"
                {...register("password", {
                  required: "Password is required",
                  minLength: {
                    value: 6,
                    message: "Password must be at least 6 characters"
                  }
                })}
              />
              {errors.password && <p className="error-login">{errors.password.message}</p>}
            </div>

            <button type="submit" className='Login-btn'>Login</button>

            {loginError && <p className="error-login">{loginError}</p>}
            {loginSuccess && <p className="loginSuccess">{loginSuccess}</p>}
          </form>
        </div>

        <div className="LoginImageBox">
          <img src={Loginimg} alt="Login Visual" className="LoginImage" />
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
