import axios from "./axios";

export const createEventoRequest = evento => axios.post(`/eventos/`, evento);

export const getEventosRequest = evento => axios.get(`/eventos/`, evento);

export const getEventoRequest = (id) => axios.get(`/eventos/${id}`);

export const updateEventoRequest = (id, data) => axios.put(`/eventos/${id}`, data);

export const deleteEventoRequest = (id) => axios.delete(`/eventos/${id}`);