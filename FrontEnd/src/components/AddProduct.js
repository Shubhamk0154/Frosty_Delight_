import React, { useState } from 'react';
import axios from 'axios';

function AddProduct() {
  const [productName, setproductName] = useState('');
  const [price, setprice] = useState('');
  const [description, setdescription] = useState('');
  const [categoryId, setcategoryId] = useState('');
  const [image, setimage] = useState('');

  const handleNameChange = event => {
    setproductName(event.target.value);
  };

  const handleImageChange = event => {
    setimage(event.target.value);
  };

  const handlePriceChange = event => {
    setprice(event.target.value);
  };

  const handleStockChange = event => {
    setdescription(event.target.value);
  };

  const handleCategoryChange = event => {
    setcategoryId(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();
    const newProduct = { productName, price, description, image };
    axios.post('http://localhost:8080/products/'+categoryId, newProduct)
      .then(response => {
        console.log(response.data);
        setproductName('');
        setprice('');
        setdescription('');
        setcategoryId('');
        setimage('');
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <form class="form-group" onSubmit={handleSubmit}>
  <div class="form-group">
    <label for="productName">Product Name:</label>
    <input class="form-control" type="text" id="productName" value={productName} onChange={handleNameChange} />
  </div>
  <div class="form-group">
    <label for="productPrice">Product Price:</label>
    <input class="form-control" type="text" id="productPrice" value={price} onChange={handlePriceChange} />
  </div>
  <div class="form-group">
    <label for="productImage">Product Image:</label>
    <input class="form-control" type="text" id="productImage" value={image} onChange={handleImageChange} />
  </div>
  <div class="form-group">
    <label for="productQuantity">Product Quantity:</label>
    <input class="form-control" type="number" id="productQuantity" value={description} onChange={handleStockChange} />
  </div>
  <div class="form-group">
    <label for="categoryId">Category ID:</label>
    <input class="form-control" type="number" id="categoryId" value={categoryId} onChange={handleCategoryChange} />
  </div>
  <button class="btn btn-primary" type="submit">Add Product</button>
</form>

    // <form onSubmit={handleSubmit}>
    //   <label>
    //     Product Name:
    //     <input type="text" value={productName} onChange={handleNameChange} />
    //   </label>
    //   <label>
    //     Product Price:
    //     <input type="text" value={price} onChange={handlePriceChange} />
    //   </label>
    //   <label>
    //     Product Image:
    //     <input type="text" value={image} onChange={handleImageChange} />
    //   </label>
    //   <label>
    //     Product Quantity:
    //     <input type="number" value={description} onChange={handleStockChange} />
    //   </label>
    //   <label>
    //     Category ID:
    //     <input type="number" value={categoryId} onChange={handleCategoryChange} />
    //   </label>
    //   <button type="submit">Add Product</button>
    // </form>
  );
}

export default AddProduct;
