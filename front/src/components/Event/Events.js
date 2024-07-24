import React from 'react';
import EventFilter from '../components/EventFilter';
import EventCard from '../components/EventCard';
import { Container, Row, Col } from 'react-bootstrap';

const events = [
  {
    image: 'https://via.placeholder.com/300x200',
    title: 'Hackaton 2024',
    startDate: '2024-07-28',
    startTime: '10:00 AM',
    daysLeft: 10,
    organizer: 'Grupo 2',
  },
  {
    image: 'https://via.placeholder.com/300x200',
    title: 'G1 Evento',
    startDate: '2024-08-05',
    startTime: '11:00 AM',
    daysLeft: 17,
    organizer: 'Grupo 1',
  }
];

function Events() {
  return (
    <Container className="events-page">
      <EventFilter />
      <Row className="mt-4">
        {events.map((event, index) => (
          <Col md={4} key={index}>
            <EventCard event={event} />
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default Events;
