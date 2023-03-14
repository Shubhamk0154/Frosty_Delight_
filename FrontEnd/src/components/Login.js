import React , {useState} from 'react';
import { useNavigate } from 'react-router-dom';
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
import AuthenticationService from '../service/AuthenticationService';
import Cart from './Cart';
import ProductAdd from './ProductAdd';
function Login() {
  let [userCred, setUserCred] = useState({ email: "", password: "" });
  const [loginStatus, setLoginStatus] = useState("");
    const navigate = useNavigate();
    const validateUser = (event) => {
        event.preventDefault();
        console.log("in validate")
        AuthenticationService.authenticateUser(userCred)
            .then((resp) => {
                console.log("Auth Successful")
                console.log(resp.data)
                console.log("User jwt", resp.data.jwt);
                console.log("role", resp.data.role);
                sessionStorage.setItem("user_data",JSON.stringify(resp.data));
                AuthenticationService.storeUserDetails(userCred.email, resp.data);
               console.log("Login User Role Check", sessionStorage.getItem("user_data"))
                navigate("/dashboard")
            })
            .catch((err) => {
                console.log("Failed Auth", err);
                setLoginStatus("wrong credentials");
            })
    }
    const handleChange = (event) => {
      const { name, value } = event.target
      setUserCred({ ...userCred, [name]: value })
  }
  return (
    <div>
    <MDBContainer className='my-5'>
      <MDBCard>

        <MDBRow className='g-0 d-flex align-items-center'>
        <MDBCol md='2'></MDBCol>
          <MDBCol md='4'>
            <MDBCardImage src='https://i.pinimg.com/originals/5d/99/48/5d9948eae55feafdd61c14d1fd443eae.jpg' 
            alt='phone' className='rounded-t-5 rounded-tr-lg-0' fluid />
          </MDBCol>
          <MDBCol md='2'></MDBCol>  
          <MDBCol md='4' >
            <form action='' onSubmit={validateUser} method="POST">
            <MDBCardBody>
            {loginStatus === "wrong credentials" && <p>Incorrect email or password</p>}
              <MDBInput wrapperClass='mb-4' label='email' id='user_name' name='email' type='text' onChange={handleChange}/>
              <MDBInput wrapperClass='mb-4' label='Password' id='password' name='password' type='password' onChange={handleChange}/>
                
            
              <div className="d-flex justify-content-evenly mx-4 mb-4 ">
               <a href="/registration">Registration</a> 
                <a href="!#">Forgot password?</a>
              </div>

              <MDBBtn type='submit' className="mb-4 w-100">Sign in</MDBBtn>

            </MDBCardBody>
            </form>
          </MDBCol>

        </MDBRow>

      </MDBCard>
    </MDBContainer>
    
    </div>
  );
    
  }
export default Login;