import React, { useState } from 'react';
import { useEffect } from "react";
import { Form, Button, Container, Spinner } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useForm} from "react-hook-form";
import { useAuth } from "../../context/AuthContext";
import 'bootstrap/dist/css/bootstrap.min.css';
import './Login.css';

function Login() {
    const {handleSubmit, formState: {errors}} = useForm();
    const [formData, setFormData] = useState({
      email: '',
      password: ''
    });
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
    };
    const {signin, errors: signInErrors, isAuthenticated, loading} = useAuth();
    const navigate = useNavigate();
    const onSubmit = async (e) => {
        e.preventDefault();
        const data = {
            email: formData.email,
            password: formData.password
        };
        signin(data);
    };
    useEffect(() => {
        if (isAuthenticated) navigate('/mis-eventos');
    }, [isAuthenticated]);

  return (
    <Container className="login-page">
        {
            signInErrors.map((error, index) => (
                <div className='errors-box' key={index} >
                    {error}
                </div>
            ))
        }
      <h2>Login</h2>
      <Form onSubmit={onSubmit}>
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
        {errors.email && <p className="text-danger"> {errors.email} </p>}

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
        {errors.password && <p className="text-danger"> Password is required </p>}

        <Button variant="primary" type="submit" disabled={loading} className="btn-block">
          {loading ? (
            <>
              <Spinner animation="border" size="sm" /> Logueando...
            </>
          ) : (
            'Login'
          )}
        </Button>
      </Form>
    </Container>
  );
}

export default Login;
