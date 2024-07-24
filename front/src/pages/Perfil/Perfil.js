import React from 'react';
import { Container, Form, Button, Spinner, Row, Col } from 'react-bootstrap';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useEffect } from 'react';
import {useAuth} from './../../context/AuthContext';
import bcrypt from 'bcryptjs';
import { useNavigate } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import'./../Register/Register.css';

function Perfil() {
    const {handleSubmit} = useForm();
    const {userDetails} = useAuth();
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const { updateUserDetails, isAuthenticated } = useAuth();

    const [formData, setFormData] = useState({
        firstName: userDetails.firstName,
        lastName: userDetails.lastName,
        dateOfBirths: userDetails.dateOfBirths,
        gender: userDetails.gender,
        email: userDetails.email,
        password: userDetails.password,
        devType: userDetails.devType,
        role: userDetails.role
    });

    const NavigateNow = () => {
      useEffect(() => {
          if (isAuthenticated) navigate('/mis-eventos');
      }, [isAuthenticated]);
      }

    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
    };

    const handleDateChange = (date) => {
      setFormData({ ...formData, dateOfBirths: date });
    };

    const formatDate = (date) => {
      const d = new Date(date);
      let month = '' + (d.getMonth() + 1);
      let day = '' + d.getDate();
      const year = d.getFullYear();

      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

      return [year, month, day].join('-');
    };

    const onSubmit = async (e) => {
      e.preventDefault();
      setLoading(true);

      const today = new Date();
      const age = today.getFullYear() - formData.dateOfBirths.getFullYear();
      const m = today.getMonth() - formData.dateOfBirths.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < formData.dateOfBirths.getDate())) {
        age--;
      }

      if (age < 18) {
        setError('Debe ser mayor de edad para registrarse.');
        setLoading(false);
        return;
      }


      try {
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(formData.password, salt);

        const userPayload = {
          firstName: formData.firstName,
          lastName: formData.lastName,
          dateOfBirths: formatDate(formData.dateOfBirths),
          gender: formData.gender,
          email: formData.email,
          password: hashedPassword,
          devType: formData.devType,
          role: formData.role
        };

        updateUserDetails(userDetails.user_Id,userPayload);
        NavigateNow();
      } catch (err) {
        console.error('Error al registrar el usuario:', err);
        setError('Hubo un error al registrar el usuario. Por favor, intente nuevamente.');
      } finally {
        setLoading(false);
      }
    };

return (
  <Container className="register-page">
    <h2>Datos del Perfil</h2>
    <Form onSubmit={onSubmit}>
      <Row>
        <Col md={6}>
          <Form.Group controlId="firstName">
            <Form.Label>Nombre</Form.Label>
            <Form.Control
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              required
            />
          </Form.Group>
        </Col>
        <Col md={6}>
          <Form.Group controlId="lastName">
            <Form.Label>Apellido</Form.Label>
            <Form.Control
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              required
            />
          </Form.Group>
        </Col>
      </Row>

      <Row>
        <Col md={6}>
          <Form.Group controlId="dateOfBirths">
            <Form.Label>Fecha de Nacimiento</Form.Label>
            <DatePicker
              selected={formData.dateOfBirths}
              onChange={handleDateChange}
              className="form-control"
              dateFormat="dd/MM/yyyy"
              required
            />
          </Form.Group>
        </Col>
        <Col md={6}>
          <Form.Group controlId="gender">
            <Form.Label>Género</Form.Label>
            <Form.Control
              as="select"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              required
            >
              <option value="">Seleccionar</option>
              <option value="Hombre">Hombre</option>
              <option value="Mujer">Mujer</option>
              <option value="Otro">Otro</option>
            </Form.Control>
          </Form.Group>
        </Col>
      </Row>

      <Form.Group controlId="email">
        <Form.Label>Email</Form.Label>
        <Form.Control
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </Form.Group>

      <Row>
        <Col md={6}>
          <Form.Group controlId="password">
            <Form.Label>Contraseña</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </Form.Group>
        </Col>
      </Row>

      <Row>
        <Col md={6}>
          <Form.Group controlId="devType">
            <Form.Label>Tipo de Desarrollador</Form.Label>
            <Form.Control
              as="select"
              name="devType"
              value={formData.devType}
              onChange={handleChange}
              required
            >
              <option value="">Seleccionar</option>
              <option value="Front">Front</option>
              <option value="Back">Back</option>
            </Form.Control>
          </Form.Group>
        </Col>
        <Col md={6}>
          <Form.Group controlId="role">
            <Form.Label>Rol</Form.Label>
            <Form.Control
              as="select"
              name="role"
              value={formData.role}
              onChange={handleChange}
              required
            >
              <option value="">Seleccionar</option>
              <option value="Usuario">Usuario</option>
              <option value="Organizador">Organizador</option>
            </Form.Control>
          </Form.Group>
        </Col>
      </Row>

      {error && <p className="text-danger">{error}</p>}

      <Button variant="primary" type="submit" disabled={loading} className="btn-block">
        {loading ? (
          <>
            <Spinner animation="border" size="sm" /> Registrando...
          </>
        ) : (
          'Registrarse'
        )}
      </Button>
    </Form>
  </Container>
);
}
export default Perfil;
