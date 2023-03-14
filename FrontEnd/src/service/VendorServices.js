import axios from 'axios';


class VendorServices {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }
    AddProduct(product,catId){
        return  axios.post(this.baseUrl+"products/"+catId,product)
    }
    AddImage(imageFile,Productid){
     return   axios.post(this.baseUrl+"product/"+Productid+"/image",imageFile,{
            headers :{
                "Accept":"*/*",
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}
export default new VendorServices();