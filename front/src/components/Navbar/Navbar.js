import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaUser } from 'react-icons/fa';
import Dropdown from 'react-bootstrap/Dropdown';
import { useAuth } from '../../context/AuthContext';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Navbar.css';

function Navbar() {
  const navigate = useNavigate();
  const { isLoggedIn, logout, user } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className="navbar">
      <div className="nav-center">
        <div className="nav-links-container">
          <ul className="nav-links">
            <li><Link to="/">Home</Link></li>
            <li><Link to="/quienes-somos">Quienes Somos</Link></li>
            <li><Link to="/eventos">Eventos</Link></li>
          </ul>
        </div>
      </div>
      <div className="user-icon">
        <Dropdown>
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            <FaUser size={24} />
          </Dropdown.Toggle>
          <Dropdown.Menu>
            {isLoggedIn ? (
              <>
                <Dropdown.Item as={Link} to={'/perfil/'+ user.user_Id}>Perfil</Dropdown.Item>
                <Dropdown.Item as={Link} to="/mis-eventos">Mis Eventos</Dropdown.Item>
                <Dropdown.Item onClick={handleLogout}>Cerrar Sesión</Dropdown.Item>
              </>
            ) : (
              <>
                <Dropdown.Item as={Link} to="/login">Ingresar</Dropdown.Item>
                <Dropdown.Item as={Link} to="/registro">Registrarse</Dropdown.Item>
              </>
            )}
          </Dropdown.Menu>
        </Dropdown>
      </div>
    </nav>
  );
}

export default Navbar;
