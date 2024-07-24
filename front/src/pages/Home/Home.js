import React from 'react';
import { Container, Row, Col, Card, Carousel, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Home.css';

function Home() {
  return (
    <Container className="home-page">
      <h1 className="text-center my-4">Bienvenido a la plataforma de eventos</h1>

      <Carousel className="mb-4">
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://via.placeholder.com/800x400"
            alt="First slide"
          />
          <Carousel.Caption>
            <h3>Evento Principal 1</h3>
            <p>Descripción del evento principal 1.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://via.placeholder.com/800x400"
            alt="Second slide"
          />
          <Carousel.Caption>
            <h3>Evento Principal 2</h3>
            <p>Descripción del evento principal 2.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="https://via.placeholder.com/800x400"
            alt="Third slide"
          />
          <Carousel.Caption>
            <h3>Evento Principal 3</h3>
            <p>Descripción del evento principal 3.</p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>

      <Row className="mb-4">
        <Col md={8}>
          <h2>Feed de Noticias</h2>
          <Card className="mb-3">
            <Card.Body>
              <Card.Title>Noticia 1</Card.Title>
              <Card.Text>
                Aquí va una breve descripción de la noticia 1 sobre eventos.
              </Card.Text>
              <Button variant="primary">Leer más</Button>
            </Card.Body>
          </Card>
          <Card className="mb-3">
            <Card.Body>
              <Card.Title>Noticia 2</Card.Title>
              <Card.Text>
                Aquí va una breve descripción de la noticia 2 sobre eventos.
              </Card.Text>
              <Button variant="primary">Leer más</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <h2>Eventos Destacados</h2>
          <Card className="mb-3">
            <Card.Img variant="top" src="https://via.placeholder.com/300x200" />
            <Card.Body>
              <Card.Title>Evento Destacado 1</Card.Title>
              <Card.Text>
                Descripción breve del evento destacado 1.
              </Card.Text>
            </Card.Body>
          </Card>
          <Card className="mb-3">
            <Card.Img variant="top" src="https://via.placeholder.com/300x200" />
            <Card.Body>
              <Card.Title>Evento Destacado 2</Card.Title>
              <Card.Text>
                Descripción breve del evento destacado 2.
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      <h2 className="text-center mb-4">Testimonios</h2>
      <Row className="mb-4">
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Text>
                "Gran evento, bien organizado y muy informativo."
              </Card.Text>
              <Card.Footer className="text-muted">- Juan Pérez</Card.Footer>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Text>
                "Aprendí mucho y conocí a gente increíble."
              </Card.Text>
              <Card.Footer className="text-muted">- María García</Card.Footer>
            </Card.Body>
          </Card>
        </Col>
        <Col md={4}>
          <Card>
            <Card.Body>
              <Card.Text>
                "Una experiencia inolvidable, ¡volveré el próximo año!"
              </Card.Text>
              <Card.Footer className="text-muted">- Carlos López</Card.Footer>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default Home;
