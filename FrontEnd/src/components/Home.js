
import React, { useEffect, useState } from 'react'
import { Row } from 'react-bootstrap';
import UserServices from '../service/UserServices';
import Footer from './Footer'
import PageRouter from './PageRouter'
import Product from './Product'


function Home() {

  let [product , setProduct] =useState([{}]);

  useEffect(() => {
    UserServices.getAllProduct().then((resp) => {
      console.log(resp.data[0].email+"inside home request")
        setProduct(resp.data)
    }).catch((err) => {
        console.log("Employee Profile Image Err", err)
    })
}, [])

  return (
        
    <div >
      <img src='/images/icecreams.jpg' width={1200} ></img>
       {/* <img src='https://lh3.googleusercontent.com/proxy/Od3tjVlimMTG4D1V-wjMhwEsWBWdMzm_PzIZeCr0ZlmN1JVp_mo-cvsUmYfy81RS_392Jwxwo1g9ylWxk61Esik23a1lSrAv5R97BLOaP2PmtnGPi27UE-nKh0ONv6rBfRfEZtUMiQvdtE98OdxULfs3iKM7f5Ur-ZAB=w1200-h630-p-k-no-nu'></img> */}
        <PageRouter></PageRouter>
        <div className=''>
          <main className='col-lg-10' style={{margin:"25px 50px 75px 100px"}}>
            <div className='row'>
            {/* <div className='col-lg-2 col-md-6 col-sm-6'></div> */}
        {
          product.map((p)=>{
            return (
              <div className='col-lg-2 col-md-6 col-sm-6'>
                <Product Product={p}></Product>
              </div>
            )
          })

          }
           {/* <div className='col-lg-1 col-md-6 col-sm-6'></div> */}
          </div>
        </main>
       </div>
    </div>
  )
}

export default Home