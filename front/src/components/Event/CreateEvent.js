import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, Spinner, Tabs, Tab } from 'react-bootstrap';
import DatePicker from 'react-datepicker';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import 'react-datepicker/dist/react-datepicker.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import './CreateEvent.css';
import {useEventos} from '../../context/EventosContext';

function CreateEvent() {
  const [createEvento] = useEventos();
  const [formData, setFormData] = useState({
    nombre: '',
    tipo: '',
    maxIntegrantesPorEquipo: '',
    minIntegrantesPorEquipo: '',
    banner: '',
    contactos: '',
    descripcion: '',
    reglas: '',
    fechaInscripcion: new Date()
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const [key, setKey] = useState('general');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleDateChange = (date) => {
    setFormData({ ...formData, fechaInscripcion: date });
  };

  const handleDescriptionChange = (value) => {
    setFormData({ ...formData, descripcion: value });
  };

  const handleRulesChange = (value) => {
    setFormData({ ...formData, reglas: value });
  };

  const handleContactosChange = (value) => {
    setFormData({ ...formData, contactos: value });
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData({ ...formData, banner: reader.result });
      };
      reader.readAsDataURL(file);
    }
  };

  const formatDate = (date) => {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('/');
  };

  const isEven = (number) => number % 2 === 0;

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    if (!isEven(parseInt(formData.maxIntegrantesPorEquipo)) || !isEven(parseInt(formData.minIntegrantesPorEquipo))) {
      setError('Los valores de máximo y mínimo de integrantes deben ser números pares.');
      setLoading(false);
      return;
    }

    const eventPayload = {
      nombre: formData.nombre,
      tipo: formData.tipo,
      maxIntegrantesPorEquipo: parseInt(formData.maxIntegrantesPorEquipo),
      minIntegrantesPorEquipo: parseInt(formData.minIntegrantesPorEquipo),
      banner: formData.banner,
      contactos: formData.contactos,
      descripcion: formData.descripcion,
      reglas: formData.reglas,
      fechaInscripcion: formatDate(formData.fechaInscripcion)
    };

    console.log('Payload to send:', eventPayload);

    try {
      const response = await createEvento(eventPayload);
      console.log('Response from API:', response);
      setSuccess(true);
      setError('');
      setFormData({
        nombre: '',
        tipo: '',
        maxIntegrantesPorEquipo: '',
        minIntegrantesPorEquipo: '',
        banner: '',
        contactos: '',
        descripcion: '',
        reglas: '',
        fechaInscripcion: new Date()
      });
    } catch (err) {
      console.error('Error al crear el evento:', err);
      setError('Hubo un error al crear el evento. Por favor, intente nuevamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="create-event-page">
      <h2>Crear Evento</h2>
      <Form onSubmit={handleSubmit}>
        <Tabs activeKey={key} onSelect={(k) => setKey(k)} className="mb-3">
          <Tab eventKey="general" title="General">
            <Row>
              <Col md={6}>
                <Form.Group controlId="nombre">
                  <Form.Label>Nombre del evento</Form.Label>
                  <Form.Control
                    type="text"
                    name="nombre"
                    value={formData.nombre}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="tipo">
                  <Form.Label>Tipo de evento</Form.Label>
                  <Form.Control
                    as="select"
                    name="tipo"
                    value={formData.tipo}
                    onChange={handleChange}
                    required
                  >
                    <option value="">Seleccionar</option>
                    <option value="Hackaton">Hackaton</option>
                    <option value="Evento Generico 1">Evento Generico 1</option>
                    <option value="Evento Generico 2">Evento Generico 2</option>
                  </Form.Control>
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group controlId="maxIntegrantesPorEquipo">
                  <Form.Label>Máximo de integrantes por equipo</Form.Label>
                  <Form.Control
                    type="number"
                    name="maxIntegrantesPorEquipo"
                    value={formData.maxIntegrantesPorEquipo}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="minIntegrantesPorEquipo">
                  <Form.Label>Mínimo de integrantes por equipo</Form.Label>
                  <Form.Control
                    type="number"
                    name="minIntegrantesPorEquipo"
                    value={formData.minIntegrantesPorEquipo}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={12}>
                <Form.Group controlId="banner">
                  <Form.Label>Banner (imagen PNG o JPG)</Form.Label>
                  <Form.Control
                    type="file"
                    name="banner"
                    accept=".png, .jpg, .jpeg"
                    onChange={handleFileChange}
                  />
                </Form.Group>
              </Col>
            </Row>
          </Tab>

          <Tab eventKey="contactos" title="Contactos">
            <Row>
              <Col md={12}>
                <Form.Group controlId="contactos">
                  <Form.Label>Contactos</Form.Label>
                  <ReactQuill
                    value={formData.contactos}
                    onChange={handleContactosChange}
                    modules={CreateEvent.modules}
                    formats={CreateEvent.formats}
                  />
                </Form.Group>
              </Col>
            </Row>
          </Tab>

          <Tab eventKey="descripcion" title="Descripción">
            <Row>
              <Col md={12}>
                <Form.Group controlId="descripcion">
                  <Form.Label>Descripción</Form.Label>
                  <ReactQuill
                    value={formData.descripcion}
                    onChange={handleDescriptionChange}
                    modules={CreateEvent.modules}
                    formats={CreateEvent.formats}
                  />
                </Form.Group>
              </Col>
            </Row>
          </Tab>

          <Tab eventKey="reglas" title="Reglas">
            <Row>
              <Col md={12}>
                <Form.Group controlId="reglas">
                  <Form.Label>Reglas</Form.Label>
                  <ReactQuill
                    value={formData.reglas}
                    onChange={handleRulesChange}
                    modules={CreateEvent.modules}
                    formats={CreateEvent.formats}
                  />
                </Form.Group>
              </Col>
            </Row>
          </Tab>
        </Tabs>

        {error && <p className="text-danger">{error}</p>}
        {success && <p className="text-success">Evento creado con éxito</p>}

        <Button variant="primary" type="submit" disabled={loading} className="btn-block">
          {loading ? (
            <>
              <Spinner animation="border" size="sm" /> Creando...
            </>
          ) : (
            'Crear Evento'
          )}
        </Button>
      </Form>
    </Container>
  );
}

CreateEvent.modules = {
  toolbar: [
    [{ 'header': '1' }, { 'header': '2' }, { 'font': [] }],
    [{ size: [] }],
    ['bold', 'italic', 'underline', 'strike', 'blockquote'],
    [{ 'list': 'ordered' }, { 'list': 'bullet' },
    { 'indent': '-1' }, { 'indent': '+1' }],
    ['link', 'image', 'video'],
    ['clean']
  ],
};

CreateEvent.formats = [
  'header', 'font', 'size',
  'bold', 'italic', 'underline', 'strike', 'blockquote',
  'list', 'bullet', 'indent',
  'link', 'image', 'video'
];

export default CreateEvent;
