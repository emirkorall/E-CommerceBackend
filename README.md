# E-Commerce Backend

A robust, production-ready e-commerce backend built with Java Spring Boot, JWT authentication, PostgreSQL, and Docker. Designed for scalability, security, and modern development workflows. This project is a showcase of my backend engineering skills and is suitable for real-world deployment.

---

## 🚀 About the Project
This application powers the backend of an e-commerce platform, providing secure user authentication, product management, order processing, and more. It is fully containerized and includes a CI/CD pipeline for automated testing and deployment.

---

## ✨ Features
- User authentication & authorization (JWT-based)
- Role-based access control (User/Admin)
- Product, order, cart, and address management
- Secure password hashing (BCrypt)
- RESTful API design
- Centralized exception handling
- Environment variable-based configuration
- Dockerized for easy deployment
- Automated CI/CD with GitHub Actions

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- Docker & Docker Compose
- Maven
- GitHub Actions (CI/CD)

---

## 🏁 Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 17 (for local development)
- Maven

### Clone the repository
```sh
git clone https://github.com/emirkorall/E-CommerceBackend.git
cd E-CommerceBackend
```

### Configuration
1. Copy the template and fill in your secrets:
   ```sh
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```
2. Or create a `.env` file in the root with your environment variables:
   ```env
   POSTGRES_DB=your_db
   POSTGRES_USER=your_user
   POSTGRES_PASSWORD=your_password
   SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/your_db
   SPRING_DATASOURCE_USERNAME=your_user
   SPRING_DATASOURCE_PASSWORD=your_password
   JWT_SECRET_KEY=your_jwt_secret
   ```

---

## 🐳 Running with Docker
```sh
docker-compose up --build
```
- Backend: [http://localhost:8080](http://localhost:8080)
- Database: [localhost:5433](localhost:5433)

---

## 📚 Example API Endpoints
- `POST /api/auth/signup` – Register a new user
- `POST /api/auth/login` – User login
- `GET /api/products` – List products
- `POST /api/orders` – Place an order (auth required)

---

## ⚙️ Environment Variables
- `POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD` – Database config
- `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` – Spring Boot DB config
- `JWT_SECRET_KEY` – Secret for JWT signing

---

## 🔄 CI/CD
- Automated build, test, and Docker image publishing via GitHub Actions
- See `.github/workflows/ci-cd.yml`

---

## 📄 License
This project is open source. See [LICENSE](LICENSE) if provided.

---

## 👤 Author & Contact
**Emir Koral**  
[GitHub](https://github.com/emirkorall/E-CommerceBackend)  
[LinkedIn](https://www.linkedin.com/in/emirkoral/)  

*Looking for backend engineering opportunities!*