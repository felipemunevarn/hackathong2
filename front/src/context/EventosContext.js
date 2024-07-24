import { createContext } from 'react';
import { useState, useContext } from 'react';
import { 
    createEventoRequest,
    getEventosRequest,
    getEventoRequest,
    updateEventoRequest,
    deleteEventoRequest}
    from '../api/events';
import {crearEquiposRequest,
    listarEquiposEventoRequest} from '../api/teams';
import PropTypes from 'prop-types';
import { set } from 'react-hook-form';

const EventosContext = createContext();

export const useEventos = () => {
    const context = useContext(EventosContext);
    if (!context) {
        throw new Error("useEvents must be used within a EventsProvider");
    } else {
        return context;
    }
}

export function EventosProvider({ children }) {
    const [eventos, setEventos] = useState([]);
    const [teams, setTeams] = useState([]);

    const createEvento = async (evento) => {
        try {
            const res = await createEventoRequest(evento);
            console.log(res.data);
        } catch (error) {
            console.log(error);
        }
    }

    const getEventos = async () => {
        try {
            const res = await getEventosRequest();
            setEventos(res.data);
        } catch (error) {
            console.log(error);
        }
    }

    const getEvento = async (id) => {
        try {
            const res = await getEventoRequest(id);
            return res.data
        } catch (error) {
            console.log(error)
        }
    }

    const updateEvento = async (id,evento) => {
        try {
            const res = await updateEventoRequest(id, evento);
            console.log(res)
        } catch (error) {
            console.log(error)
        }
    }

    
    const deleteEvento = async (id) => {
        try {
            const res = await deleteEventoRequest(id);
            if (res.status === 204) {
                setEventos(eventos.filter((evento) => evento._id !== id));
            }
        } catch (error) {
            console.log(error);
        }
    }
    const iniciarEvento = async (id) => {
        try {
            const res = await crearEquiposRequest(id);
            console.log(res)
        } catch (error) {
            console.log(error)
        }
    }

    const listarEquiposEventoRequest = async (id) => {
        try {
            const res = await listarEquiposEventoRequest(id);
            setTeams(res.data);
            return teams;
        } catch (error) {
            console.log(error)
        }
    }


    return (
        <EventosContext.Provider
            value={{
                eventos,
                setEventos,
                createEvento,
                deleteEvento,
                updateEvento,
                useEventos,
                getEventos,
                getEvento,
                iniciarEvento,
                listarEquiposEventoRequest
            }}
    >
        {children}
    </EventosContext.Provider>
  );
}

EventosProvider.propTypes = {
    children: PropTypes.node.isRequired
}