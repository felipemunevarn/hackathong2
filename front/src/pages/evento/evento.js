import React from 'react';
import { useEventos } from '../../context/EventosContext';
import {useNavigate, useParams} from 'react-router-dom';

function Evento() {
    const navigate = useNavigate();
    const params = useParams();
    const {listarEquiposEventoRequest, teams, getEvento} = useEventos();
    const evento = getEvento(params.id);
    if(params.id) {
        listarEquiposEventoRequest(params.id);
    }
    return (
        <div>
            <h1>{evento.nombre}</h1>
            <p>{evento.fechaCreacion}</p>
            <p>{evento.maxIntegrantesPorEquipo}</p>
            <p>{evento.minIntegrantesPorEquipo}</p>
            <p>{evento.estado}</p>

            <h2>Equipos</h2>
            <ul>
                {teams.map((team) => (
                    <li key={team.equipoId}>{team.nombreEquipo}{team.estado}
                        <ul>
                            {team.participantes.map((participante) => (
                                <li key={participante.id}>{participante.nombre}</li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    )   
}


export default Evento;