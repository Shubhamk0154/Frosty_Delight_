import React, { useState } from 'react';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol,
  MDBInput,
  MDBCheckbox
}
from 'mdb-react-ui-kit';
import UserServices from '../service/UserServices';
import { useNavigate } from 'react-router-dom';

function Registration() {
  let [user ,setUser]= useState({firstName:"",lastName:"",email:"",password:"",mobileNumber:"",dob:"",roles : []});
 let [role,setRole]=useState({roles : []});
  const handleChange = (event) => {
    const { name, value } = event.target
    setUser({ ...user, [name]: value })
    console.log(user+"user")
  }
  const navigate = useNavigate();
  const handleChange1 = (e) => {
    const { value, checked } = e.target;
    const { roles } = role;
      
    console.log(`${value} is ${checked}`);
     
    // Case 1 : The user checks the box
    if (checked) {
      setUser({
        ...user,roles: [...roles, value],
        
      });
      
    }
  
    // Case 2  : The user unchecks the box
    else {
      setUser({
        ...user,roles: roles.filter((e) => e !== value),
      });
    }
  };    
    const registerUser=(event)=>{
      event.preventDefault();
    // checkAll();
    
      console.log(user.roles+"fghdj");
      console.log("inside register user")
      UserServices.registerUSer(user).then(
        (result)=>console.log(result.data),
      navigate("/login")
      ).catch(()=> {})
    }
  return (
    <MDBContainer className='my-5'>
      <MDBCard>

        <MDBRow className='g-0 d-flex align-items-center'>
        <MDBCol md='2'></MDBCol>
          <MDBCol md='4'>
            <MDBCardImage src='https://www.indianhealthyrecipes.com/wp-content/uploads/2022/03/butterscotch-ice-cream-recipe.jpg' 
            alt='phone' className='rounded-t-5 rounded-tr-lg-0' fluid />
          </MDBCol>
          <MDBCol md='2'></MDBCol>  
          <MDBCol md='4' >
            <form action='/' onSubmit={registerUser} method="POST">
            <MDBCardBody>
              <MDBInput wrapperClass='mb-4' label='Fisrt Name' id='firstName' type='text' onChange={handleChange} name="firstName" />
              <MDBInput wrapperClass='mb-4' label='Last Name' id='lastName' type='text' onChange={handleChange} name="lastName"/>
              <MDBInput wrapperClass='mb-4' label='Email address' id='email' type='email' onChange={handleChange} name="email"/>
              <MDBInput wrapperClass='mb-4' label='Password' id='password' type='password' onChange={handleChange} name="password"/>
              <MDBInput wrapperClass='mb-4' label='Mobile Number' id='mobileNumber' type='number' onChange={handleChange} name="mobileNumber"/>
              <MDBInput wrapperClass='mb-4' label='Date Of Birth' id='dob' type='date' onChange={handleChange} name="dob"/>
              <div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" name="roles" id="ROLE_ADMIN" value="ROLE_ADMIN" onChange={handleChange1}  />
  <label class="form-check-label" for="ROLE_ADMIN">Admin</label>
</div>

<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" name="roles" id="ROLE_USER" value="ROLE_USER" onChange={handleChange1} />
  <label class="form-check-label" for="ROLE_USER">Customer</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" name="roles" id="ROLE_VENDOR" value="ROLE_VENDOR" onChange={handleChange1}/>
  <label class="form-check-label" for="ROLE_VENDOR">Vendor</label>
</div>

              <MDBBtn type='submit' className="mb-4 w-100">Register</MDBBtn>

            </MDBCardBody>
          </form>
          </MDBCol>

        </MDBRow>

      </MDBCard>
    </MDBContainer>
  );
}

export default Registration;