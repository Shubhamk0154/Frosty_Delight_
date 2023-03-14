import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const [email, setUseremail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleUseremailChange = (event) => {
    setUseremail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const admin = { email, password };
    try {
      if (email=='Vishal123@gmail.com') {
        const response = await axios.post('http://localhost:8080/admin/login', admin)
      .then(response => {
        console.log(response.data);
        setUseremail('');
        setPassword('');
        navigate("/addfunction");
      })

      console.log(response.data);
      } else {
        const response = await axios.post('http://localhost:8080/customer/login', admin)
      .then(response => {
        console.log(response.data);
        setUseremail('');
        setPassword('');
        navigate("/");
      })

      console.log(response.data);
      }
      
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="username">Useremail:</label>
        <input type="text" id="username" value={email} onChange={handleUseremailChange} />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input type="password" id="password" value={password} onChange={handlePasswordChange} />
      </div>
      <button type="submit">Login</button>
    </form>
  );
};

export default Login;