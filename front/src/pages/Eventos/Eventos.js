import React, { useState, useEffect } from 'react';
import axios from 'axios';
import EventFilter from '../../components/Event/EventFilter';
import EventCard from '../../components/Event/EventCard';
import { Container, Row, Col } from 'react-bootstrap';
import './Eventos.css';

function Eventos() {
  const [events, setEvents] = useState([]);
  const [filteredEvents, setFilteredEvents] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get('https://hackaton-3cnf.onrender.com/eventos');
        setEvents(response.data);
        setFilteredEvents(response.data);
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    };

    fetchEvents();
  }, []);

  
  const handleFilterChange = (filters) => {
    let filtered = events;
    if (filters.eventType) {
      filtered = filtered.filter(event => event.tipo === filters.eventType);
    }
    if (filters.startDate) {
      filtered = filtered.filter(event => new Date(event.fechaCreacion) >= new Date(filters.startDate));
    }
    if (filters.endDate) {
      filtered = filtered.filter(event => new Date(event.fechaCreacion) <= new Date(filters.endDate));
    }
    setFilteredEvents(filtered);
  };

  return (
    <Container className="events-page">
      <div className="filter-container">
        <EventFilter onFilterChange={handleFilterChange} />
      </div>
      <Row className="mt-4">
        {filteredEvents.map((event, index) => (
          <React.Fragment key={index}>
            {index === 0 || filteredEvents[index - 1].tipo !== event.tipo ? (
              <Col xs={12} className="mb-2">
                <h3>{event.tipo}</h3>
              </Col>
            ) : null}
            <Col md={4} className="d-flex align-items-stretch mb-4">
              <EventCard event={event} />
            </Col>
          </React.Fragment>
        ))}
      </Row>
    </Container>
    


  );
}

export default Eventos;
