# E-Commerce Backend

A robust, production-ready e-commerce backend built with Java Spring Boot, JWT authentication, PostgreSQL, and Docker. Designed for scalability, security, and modern development workflows. This project is a showcase of my backend engineering skills and is suitable for real-world deployment.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)

---

## 📋 Table of Contents
- [About the Project](#-about-the-project)
- [Features](#-features)
- [Tech Stack](#️-tech-stack)
- [Getting Started](#️-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Environment Variables](#️-environment-variables)
- [CI/CD](#-cicd)
- [Contributing](#-contributing)
- [License](#-license)
- [Author & Contact](#-author--contact)

---

## 🚀 About the Project
This application powers the backend of an e-commerce platform, providing secure user authentication, product management, order processing, and more. It is fully containerized and includes a CI/CD pipeline for automated testing and deployment.

---

## ✨ Features
- **Authentication & Authorization**: JWT-based user authentication with role-based access control
- **User Management**: Registration, login, profile management with secure password hashing (BCrypt)
- **Product Management**: CRUD operations for products with category support
- **Shopping Cart**: Add/remove items, quantity management, cart persistence
- **Order Processing**: Complete order lifecycle from cart to fulfillment
- **Address Management**: Multiple addresses per user with shipping/billing support
- **Payment Integration**: Credit card management (ready for payment gateway integration)
- **Security**: Role-based access control (ADMIN/USER), input validation, SQL injection protection
- **API Design**: RESTful API with consistent response formats and error handling
- **Database**: PostgreSQL with proper indexing and relationships
- **Containerization**: Docker support for easy deployment and scaling
- **CI/CD**: Automated testing and deployment pipeline

---

## 🛠️ Tech Stack
- **Backend Framework**: Java 17, Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: PostgreSQL 15
- **ORM**: Spring Data JPA, Hibernate
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose
- **CI/CD**: GitHub Actions
- **Testing**: JUnit 5, Mockito

---

## 🏁 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- PostgreSQL 15+ (for local development)
- Docker & Docker Compose (for containerized setup)

### Clone the repository
```bash
git clone https://github.com/emirkorall/E-CommerceBackend.git
cd E-CommerceBackend
```

### Configuration
1. Create a `.env` file in the root with your environment variables:
   ```env
   POSTGRES_DB=ecommerce_db
   POSTGRES_USER=ecommerce_user
   POSTGRES_PASSWORD=your_secure_password
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ecommerce_db
   SPRING_DATASOURCE_USERNAME=ecommerce_user
   SPRING_DATASOURCE_PASSWORD=your_secure_password
   JWT_SECRET_KEY=your_super_secret_jwt_key_here
   SPRING_JPA_HIBERNATE_DDL_AUTO=update
   SPRING_JPA_SHOW_SQL=false
   ```

---

## 🐳 Running with Docker (Recommended)
```bash
# Build and start all services
docker-compose up --build

# Run in background
docker-compose up -d --build

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

**Access Points:**
- Backend API: [http://localhost:8080](http://localhost:8080)
- Database: localhost:5433 (if exposed)

## ▶️ Running Locally (Without Docker)
1. **Start PostgreSQL** and create the database:
   ```sql
   CREATE DATABASE ecommerce_db;
   CREATE USER ecommerce_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE ecommerce_db TO ecommerce_user;
   ```

2. **Set environment variables** (see Configuration section above)

3. **Start the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API** at [http://localhost:8080](http://localhost:8080)

## ▶️ Running Locally (Without Docker)
1. Ensure PostgreSQL is running and environment variables are set (see `.env` example above).
2. Start the backend:
   ```sh
   ./mvnw spring-boot:run
   ```

---

## 📚 API Documentation

### Authentication Endpoints
```http
POST /api/auth/signup
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123",
  "authority": "USER"
}

POST /api/auth/login
Content-Type: application/json

{
  "username": "user@example.com",
  "password": "password123"
}
```

### Product Endpoints
```http
GET    /api/products          # List all products
GET    /api/products/{id}     # Get product by ID
POST   /api/products          # Create product (ADMIN only)
PUT    /api/products/{id}     # Update product (ADMIN only)
DELETE /api/products/{id}     # Delete product (ADMIN only)
```

### Cart Endpoints
```http
GET    /api/cart              # Get user's cart
POST   /api/cart/items        # Add item to cart
PUT    /api/cart/items/{id}   # Update cart item quantity
DELETE /api/cart/items/{id}   # Remove item from cart
```

### Order Endpoints
```http
GET    /api/orders            # Get user's orders
POST   /api/orders            # Place new order
GET    /api/orders/{id}       # Get order details
```

### User Management
```http
GET    /api/users             # Get all users (ADMIN only)
GET    /api/users/{id}        # Get user profile
PUT    /api/users/{id}        # Update user profile
```

**Authentication**: Include `Authorization: Bearer <jwt_token>` header for protected endpoints.

---

## 🗄️ Database Schema

### Core Tables
- **users**: User accounts with authentication info
- **roles**: User roles (ADMIN, USER)
- **products**: Product catalog with pricing and inventory
- **carts**: Shopping cart sessions
- **cart_items**: Individual items in carts
- **orders**: Order records
- **order_products**: Order line items
- **addresses**: User shipping/billing addresses
- **cards**: Payment card information

### Key Relationships
- Users have one role (authority)
- Users can have multiple addresses and cards
- Carts belong to users and contain cart items
- Orders reference users and contain order products
- Products are referenced by cart items and order products

---

## 🧪 Testing

### Run Tests Locally
```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=AuthServiceTest

# Run with coverage
./mvnw test jacoco:report
```

### Test Coverage
- Unit tests for services
- Integration tests for controllers
- Repository tests for data access

---

## 🚀 Deployment

### Render Deployment
1. **Create a new Web Service** on Render
2. **Connect your GitHub repository**
3. **Configure environment variables**:
   - `SPRING_DATASOURCE_URL`: Your Render PostgreSQL URL
   - `SPRING_DATASOURCE_USERNAME`: Database username
   - `SPRING_DATASOURCE_PASSWORD`: Database password
   - `JWT_SECRET_KEY`: Your JWT secret
4. **Set build command**: `./mvnw clean package`
5. **Set start command**: `java -jar target/ecommerce-0.0.1-SNAPSHOT.jar`

### Docker Deployment
```bash
# Build image
docker build -t yourusername/ecommerce:latest .

# Push to registry
docker push yourusername/ecommerce:latest

# Deploy
docker run -p 8080:8080 --env-file .env yourusername/ecommerce:latest
```

---

## ⚙️ Environment Variables

| Variable | Description | Required | Default |
|----------|-------------|----------|---------|
| `POSTGRES_DB` | Database name | Yes | - |
| `POSTGRES_USER` | Database username | Yes | - |
| `POSTGRES_PASSWORD` | Database password | Yes | - |
| `SPRING_DATASOURCE_URL` | JDBC connection URL | Yes | - |
| `SPRING_DATASOURCE_USERNAME` | Spring DB username | Yes | - |
| `SPRING_DATASOURCE_PASSWORD` | Spring DB password | Yes | - |
| `JWT_SECRET_KEY` | JWT signing secret | Yes | - |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Hibernate DDL mode | No | `update` |
| `SPRING_JPA_SHOW_SQL` | Show SQL queries | No | `false` |

---

## 🔄 CI/CD
- **Automated Testing**: Runs on every push and pull request
- **Docker Image Building**: Creates and pushes Docker images
- **Deployment**: Automatic deployment to staging/production
- **Quality Gates**: Code coverage and security scanning

See `.github/workflows/ci-cd.yml` for detailed pipeline configuration.

---

## 🤝 Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write unit tests for new features
- Update documentation for API changes
- Ensure all tests pass before submitting PR

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author & Contact
**Emir Koral**  
[GitHub](https://github.com/emirkorall/E-CommerceBackend)  
[LinkedIn](https://www.linkedin.com/in/emirkoral/)  

*Looking for backend engineering opportunities!*

---

## 🙏 Acknowledgments
- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database
- Docker team for containerization tools