import axios from "./axios";

export const subscriptionRequest = async (evento, user)=> axios.post(`/inscripciones/${evento}/${user}`);

export const deleteSubscriptionRequest = async (subscription) => axios.delete(`/inscripciones/${subscription}`);