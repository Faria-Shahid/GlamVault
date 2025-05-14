import React, { useState, useEffect } from 'react';
import { useForm } from "react-hook-form";
import './ContactUs.css';

const ContactUs = () => {
  const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm();
  const [message, setMessage] = useState(""); 

  const onSubmit = (data) => {
    console.log("Form Submitted", data);
    setMessage("Your response is fowarded!"); 
    setTimeout(() => {
      setMessage("");
    }, 5000);
  };

  return (
    <div className='ContactUs-Container'>
      <div className="text">
        <h2 className='ClassHeading'>Contact Us</h2>
        <div className="container">
          <form onSubmit={handleSubmit(onSubmit)}>
            <input
              {...register("name", {
                required: { value: true, message: "Name is required" },
                minLength: { value: 3, message: "Enter a valid name" }
              })}
              type="text"
              placeholder="Name"
            />
            <input
              {...register("email", {
                required: { value: true, message: "Email is required" },
                pattern: {
                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                  message: "Enter a valid email address",
                },
              })}
              type="email"
              placeholder="Email"
            />
            <input
              {...register("message", {
                required: { value: true, message: "Message is required" },
              })}
              type="text"
              placeholder="Message"
              className='ClassMessage'
            />
            {errors.name && <p className="error">{errors.name.message}</p>}
            {errors.email && <p className="error">{errors.email.message}</p>}
            <input disabled={isSubmitting} type="submit" value="Submit" />
            {message && <p className="success">{message}</p>}
          </form>
        </div>
      </div>
    </div>
  );
};

export default ContactUs;
