import axios from "./axios";

export const registerRequest = user => axios.post(`/auth/signup`, user);

export const loginRequest = user => axios.post(`/auth/login`, user);

export const getUserRequest = (id) => axios.get(`/user/${id}`);

export const updateUserDetails = (id, data) => axios.put(`/user/${id}`, data);
