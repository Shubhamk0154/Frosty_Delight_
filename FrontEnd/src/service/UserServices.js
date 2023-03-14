import axios from 'axios';


class UserServices {
    constructor() {
        this.baseUrl = "http://localhost:8080/"
    }

    getUserByUserName(userName) {
        let role = sessionStorage.getItem("user_role") === "APPLICANT" ? "applicant/" : "employee/"
        return axios.get(this.baseUrl + role + "profile/" + userName);
    }

    getNameByUserName(userName) {
        let role = sessionStorage.getItem("user_role") === "APPLICANT" ? "applicant/" : "employee/"
        console.log(this.baseUrl)
        return axios.get(this.baseUrl + role + "name/" + userName);
    }

    getProfileImageByUserName(Productid) {

        return axios.get(this.baseUrl +"products/"+Productid+"/image", { responseType: 'blob' })
    }

    getAllProduct(){
        return axios.get(this.baseUrl+"products")
    }
    registerUSer(user){
        return axios.post(this.baseUrl+"api/signup",user)
    }
    getallratings(productId){
        return axios.get(this.baseUrl+"review1/"+productId)
    }
    getAllUser(){
        return axios.get(this.baseUrl+"users")

    }
    addTocart(cartItem){
        return axios.post(this.baseUrl+"cart/cartItem",cartItem)
    }
}

export default new UserServices();