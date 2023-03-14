import Admin from "./dashboard/Admin";
import Vendor from "./dashboard/Vendor";
import UnAuthorizedError401 from "./dashboard/UnAuthorizedError401";
import Home from "./Home";


const AuthorizationRouter = () => {
    return (
        sessionStorage.getItem("role") === "ROLE_ADMIN" ?
            <Admin></Admin> :
            sessionStorage.getItem("role") === "ROLE_VENDOR" ?
                <Vendor></Vendor> :
                sessionStorage.getItem("role") === "ROLE_USER" ?
                    <Home></Home>:
<UnAuthorizedError401></UnAuthorizedError401>
    )
}

export default AuthorizationRouter;

