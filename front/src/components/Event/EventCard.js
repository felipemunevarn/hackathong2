import React from 'react';
import { useSubscription} from './../../context/SubscriptionContext';
import { useAuth } from '../../context/AuthContext';
import { Card, Button, Alert} from 'react-bootstrap';
import './EventCard.css';
import { useNavigate } from 'react-router-dom';
import { useEventos } from '../../context/EventosContext';

function formatDate(dateString) {
  const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
  return new Date(dateString).toLocaleDateString('es-ES', options);
}
function EventCard({ event }) {
  const [showAlert, setShowAlert] = React.useState(false);
  const {subscribe, isSubscribed, subscription} = useSubscription();
  const { isAuthenticated, user } = useAuth();
  const { iniciarEvento } = useEventos();
  const navigate = useNavigate();

  const handleSubscribe = () => {
    if (!isAuthenticated) {
      navigate('/login');
      return;
    }
    if (isSubscribed) {
      // Si ya está inscrito, mostrar aviso temporal y luego ocultarlo
      setShowAlert(true);
      setTimeout(() => {
      setShowAlert(false);
      }, 3000); // 3 segundos
    } else {
      // Si no está inscrito, simular la inscripción y mostrar aviso de inscripción exitosa
      subscribe({ evento: event.id, usuario: user.id });
      setShowAlert(true);
      setTimeout(() => {
        setShowAlert(false);
      }, 3000); // 3 segundos
    }
  };
  const iniciar = (id) => {
    iniciarEvento(id);
    navigate('/evento/'+id);
  }

  return (
    <Card className="event-card">
      <Card.Img variant="top" src={event.banner || 'https://via.placeholder.com/300x200'} alt="Event Image" />
      <Card.Body>
        <Card.Title>{event.nombre}</Card.Title>
        <Card.Text>
          <strong>Fecha de creación:</strong> {formatDate(event.fechaCreacion)} <br />
          <strong>Estado:</strong> {event.estado} <br />
          <strong>Organizador:</strong> {event.organizador}
        </Card.Text>
      
    {isAuthenticated && ( event.id in subscription) ? (
      <div className='botones'>
        <Button onClick={handleSubscribe} variant="delete" disabled>Eliminar</Button>
        <Button onClick={iniciar(event.id)} variant="success" disabled>Iniciar</Button>
      </div>
      
    ) : (
      <Button variant="primary" onClick={handleSubscribe}>Inscribirse</Button>
    )
  }
  {showAlert && (
    <Alert variant={(event.id in subscription) ? 'success' : 'info'} className="mt-3">
      {isSubscribed ? 'Inscripción exitosa.' : 'No es posible que se inscribirse dos veces' }
    </Alert>
  )}
  </Card.Body>
  </Card>
  );
}

export default EventCard;
