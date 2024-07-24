import { createContext, useState, useContext, useEffect} from "react";
import PropTypes from 'prop-types';
import { subscriptionRequest, deleteSubscriptionRequest } from './../api/subscription';   

export const SubscriptionContext = createContext();

export const useSubscription = () => {
    const context = useContext(SubscriptionContext);
    if (!context) throw new Error("useSubscription must be used within an SubscriptionProvider");
    return context;
}

export const SubscriptionProvider = ({children}) => {
    const [subscriptions, setSubscription] = useState([]);
    const [isSubscribed, setIsSubscribed] = useState(false);
    const [errors, setErrors] = useState([]);

    const subscribe = async(subscriptionData) => {
        try {
            if (subscriptionData.evento in subscriptions) {
                setErrors(['Ya estás inscrito en este evento']);
                return;
            }
            const res = await subscriptionRequest(subscriptionData);
            console.log(res.data);
            setSubscription(res.data);
            setIsSubscribed(true);
        } catch (error) {
            console.log(error);
        }
    }

    const unsubscribe = async(subscriptionData) => {
        try {
            if (!(subscriptionData.evento in subscriptions)) {
                setErrors(['No estás inscrito en este evento']);
                return;
            }
            const res = await deleteSubscriptionRequest(subscriptionData);
            console.log(res.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        if (errors.length > 0) {
            const timer = setTimeout(() => {
                setErrors([]);
            }, 5000);
            return () => clearTimeout(timer);
        }
    }, [errors]);

    return (
        <SubscriptionContext.Provider
        value={{
            subscribe,
            isSubscribed,
            unsubscribe,
            subscriptions,
            errors,
        }} >
            {children}
        </SubscriptionContext.Provider>
    );
};

SubscriptionProvider.propTypes = {
    children: PropTypes.node.isRequired
};