import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';
import Home from './pages/Home/Home';
import QuienesSomos from './pages/QuienesSomos/QuienesSomos';
import Eventos from './pages/Eventos/Eventos';
import Login from './pages/Login/Login';
import Register from './pages/Register/Register';
import Perfil from './pages/Perfil/Perfil';
import MyEvents from './pages/MyEvents/MyEvents';
import { AuthProvider } from "./context/AuthContext"
import { SubscriptionProvider } from './context/SubscriptionContext';
import { EventosProvider } from './context/EventosContext';
import './App.css';
import ProtectedRoute from './ProtectedRoute';

function App() {
  return (
    <AuthProvider>
      <SubscriptionProvider>
        <EventosProvider>
          <Router>
            <div className="App">
              <Navbar />
              <div className="content">
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/quienes-somos" element={<QuienesSomos />} />
                  <Route path="/eventos" element={<Eventos />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/registro" element={<Register />} />
                  <Route element={<ProtectedRoute/>} >
                    <Route path="/perfil/:id" element={<Perfil />} />
                    <Route path="/mis-eventos" element={<MyEvents />} />
                    <Route path="/mis-eventos/:id" element={<MyEvents />} />
                  </Route>
                </Routes>
              </div>
              <Footer />
            </div>
          </Router>
        </EventosProvider>
      </SubscriptionProvider>
    </AuthProvider>
  );
}

export default App;
