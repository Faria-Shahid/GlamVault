import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import './SignupPage.css';
import { Link } from 'react-router-dom';
import Signupimg from './signuppp-imgg.png'; // ✅ fixed import
import AdditionalSignup from './AdditionalSignup'; // Import AdditionalSignup Component

const SignupPage = () => {
  const { register, handleSubmit, formState: { errors }, reset, watch } = useForm();
  const [goToAdditionalSignup, setGoToAdditionalSignup] = useState(false);
  const [userData, setUserData] = useState(null); // State to hold user data

  const onSubmit = (data) => {
    console.log(data);
    setUserData(data); // Save the user data
    setGoToAdditionalSignup(true); // Move to the next step when form is submitted
    reset();
  };

  if (goToAdditionalSignup) {
    return <AdditionalSignup userData={userData} />; // Pass userData to AdditionalSignup component
  }

  return (
    <div className='SignupContainer'>
      <div className="SignupWrapper">
        <div className="SignupFormBox">
          <h2 className='Signup-heading'>Create Your Account</h2>
          <form onSubmit={handleSubmit(onSubmit)} className="signup-form">

            {/* Email Input */}
            <div className='SP-div'>
              <label htmlFor="signup-email">Email:</label>
              <input
                id="signup-email"
                type="email"
                placeholder="Enter your email"
                {...register("email", {
                  required: "Email is required",
                  pattern: {
                    value: /^[a-zA-Z0-9._%+-]+@[a-zAZ0-9.-]+\.[a-zA-Z]{2,}$/,
                    message: "Enter a valid email"
                  }
                })}
              />
              {errors.email && <p className="error-signup">{errors.email.message}</p>}
            </div>

            {/* Password Input */}
            <div className='SP-div'>
              <label htmlFor="signup-password">Password:</label>
              <input
                id="signup-password"
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
              {errors.password && <p className="error-signup">{errors.password.message}</p>}
            </div>

            {/* Confirm Password Input */}
            <div className='SP-div'>
              <label htmlFor="signup-confirm-password">Confirm Password:</label>
              <input
                id="signup-confirm-password"
                type="password"
                placeholder="Confirm your password"
                {...register("confirmPassword", {
                  required: "Please confirm your password",
                  validate: (value) =>
                    value === watch("password") || "Passwords do not match"
                })}
              />
              {errors.confirmPassword && <p className="error-signup">{errors.confirmPassword.message}</p>}
            </div>
  <div className="login-prompt">
      <h3>Already a member?  <Link to="/Login" className="Login-link">Login</Link> to your account!</h3>
    </div>
            <button type="submit" className='Signup-btn'>Continue Sign Up</button>
          </form>
        </div>

        <div className="SignupImageBox">
          <img src={Signupimg} alt="Signup Visual" className="SignupImage" />
        </div>
      </div>
    </div>
  );
};

export default SignupPage;
