import React, { useState } from 'react';
import { Form } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import './EventFilter.css';

function EventFilter({ onFilterChange }) {
  const [startDate, setStartDate] = useState(new Date('2000-01-01'));
  const [endDate, setEndDate] = useState(null);
  const [eventType, setEventType] = useState('');

  const handleFilterChange = () => {
    onFilterChange({
      eventType,
      startDate,
      endDate,
    });
  };

  return (
    <div className="filter-container">
      <Form className="event-filter d-flex" onChange={handleFilterChange}>
        <Form.Group controlId="eventType" className="mr-2">
          <Form.Control
            as="select"
            value={eventType}
            onChange={(e) => setEventType(e.target.value)}
          >
            <option value="">Tipo</option>
            <option>Hackaton</option>
            <option>Evento Generico 1</option>
            <option>Evento Generico 2</option>
          </Form.Control>
        </Form.Group>
        <Form.Group controlId="startDate" className="mr-2">
          <DatePicker
            selected={startDate}
            onChange={(date) => setStartDate(date)}
            className="form-control"
            placeholderText="Desde"
            isClearable
          />
        </Form.Group>
        <Form.Group controlId="endDate" className="mr-2">
          <DatePicker
            selected={endDate}
            onChange={(date) => setEndDate(date)}
            className="form-control"
            placeholderText="Hasta"
            isClearable
          />
        </Form.Group>
      </Form>
    </div>
  );
}

export default EventFilter;
