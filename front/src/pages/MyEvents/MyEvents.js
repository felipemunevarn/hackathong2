import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Tabs, Tab, Container, Button } from 'react-bootstrap';
import CreateEvent from '../../components/Event/CreateEvent';
import EventCard from '../../components/Event/EventCard';
import 'bootstrap/dist/css/bootstrap.min.css';
import './MyEvents.css';

function MyEvents() {
  const [key, setKey] = useState('created');
  const [showCreateEventForm, setShowCreateEventForm] = useState(false);
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get('https://hackaton-3cnf.onrender.com/eventos');
        setEvents(response.data);
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    };

    fetchEvents();
  }, []);

  const handleCreateEventClick = () => {
    setShowCreateEventForm(true);
  };

  return (
    <Container className="my-events">
      {showCreateEventForm ? (
        <CreateEvent />
      ) : (
        <>
          <h2>Mis Eventos</h2>
          <Tabs
            id="my-events-tabs"
            activeKey={key}
            onSelect={(k) => setKey(k)}
            className="mb-3"
          >
            <Tab eventKey="created" title="Eventos creados">
              <div className="tab-content">
                <Button variant="success" className="mb-3" onClick={handleCreateEventClick}>
                  Crear evento
                </Button>
                <div className="event-card-container">
                  {events.map((event, index) => (
                    <EventCard key={index} event={event} />
                  ))}
                </div>
              </div>
            </Tab>
            <Tab eventKey="past" title="Eventos pasados">
              <div className="tab-content">
                <div className="event-card-container">
                  {events.map((event, index) => (
                    <EventCard key={index} event={event} />
                  ))}
                </div>
              </div>
            </Tab>
            <Tab eventKey="mine" title="Mis eventos">
              <div className="tab-content">
                <div className="event-card-container">
                  {events.map((event, index) => (
                    <EventCard key={index} event={event} />
                  ))}
                </div>
              </div>
            </Tab>
          </Tabs>
        </>
      )}
    </Container>
  );
}

export default MyEvents;
