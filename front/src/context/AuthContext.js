import { createContext, useState, useContext, useEffect} from "react";
import { registerRequest, loginRequest, getUserRequest,verifyTokenRequest} from '../api/auth';
import Cookie from 'js-cookie';
import PropTypes from 'prop-types';

export const AuthContext = createContext();

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("useAuth must be used within an AuthProvider");
    return context;
}

export const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null);
    const [userDetails, setUserDetails] = useState(null);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [errors, setErrors] = useState([]);
    const [loading, setLoading] = useState(true);

    const signup = async(user) => {

        try {
            const res = await registerRequest(user);
            console.log(res.data);
            setUser(res.data);
            setIsAuthenticated(true);
        } catch (error) {
            setErrors(error.response.data);
        }
    }
    const signin = async(user) => {
        try {
            const res = await loginRequest(user);
            console.log(res.data);
            setUser(res.data);
            setIsAuthenticated(true);
        } catch (error) {
            console.log(error);
        }
    }

    const getUserDetails = async (id) => {
        try {
            const res = await getUserRequest(id);
            console.log(res.data);
            setUserDetails(res.data);
        } catch (error) {
            console.log(error);
        }
    }

    const updateUserDetails = async (id, data) => {
        try {
            const res = await getUserRequest(id, data);
            console.log(res.data);
            setUserDetails(res.data);
        } catch (error) {
            console.log(error);
        }
    }

    const logout = () => {
        Cookie.remove('token');
        setUser(null);
        setIsAuthenticated(false);
    }

    useEffect(() => {
        if (errors.length > 0) {
            const timer = setTimeout(() => {
                setErrors([]);
            }, 5000);
            return () => clearTimeout(timer);
        }
    }, [errors]);

    useEffect(() => {
        if (isAuthenticated) {
            getUserDetails(user.id);
        }
    }, [isAuthenticated]);

    useEffect(() => {
        async function checkLogin() {
            const cookies = Cookie.get();
            if (!cookies.token) {
                setIsAuthenticated(false);
                setUser(null);
                setLoading(false);
                return;
            } else {
                try {
                    setUser(user);
                    setIsAuthenticated(true);
                    setLoading(false);
                } catch (error) {
                    setIsAuthenticated(false);
                    setUser(null);
                    setLoading(false);
                }
            }
        }
        checkLogin();
    }, []);

    return (
        <AuthContext.Provider
        value={{
            signup,
            signin,
            logout,
            loading,
            user,
            isAuthenticated,
            errors,
        }} >
            {children}
        </AuthContext.Provider>
    );
};

AuthProvider.propTypes = {
    children: PropTypes.node.isRequired
};