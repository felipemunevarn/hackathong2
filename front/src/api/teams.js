import axios from "./axios";

export const crearEquiposRequest = (id) => axios.get(`/equipos/${id}`);

export const listarEquiposEventoRequest = (id, data) => axios.put(`/equipos/${id}`, data);