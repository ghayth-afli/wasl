# Wasl: Traveler's Connection Hub

Wasl is a web application designed to connect travelers with individuals needing packages delivered to specific destinations.

## Tech Stack

- **Frontend**:
  - React.js
  - Material-UI for styling
  - ApexCharts for data visualization
  - SimpleBar for custom scrollbar
  - React Router for navigation
  - Faker.js for generating fake data
  
- **Backend**:
  - Spring Boot
  - Spring Security for authentication
  - Spring Data JPA for data persistence
  - MySQL database
  - Stripe API for payment processing
  - JSON Web Token (JWT) for authentication

## Getting Started

### Frontend Setup

1. Install dependencies:
```bash
npm install
```
2. Run the development server:
```bash
npm run dev
```

3. Open your browser and navigate to `http://localhost:3000` to view the application.

### Backend Setup

1. Ensure you have MySQL installed and running.

2. Set up your MySQL database and configure the application.properties file in the Spring Boot project accordingly.

3. Run the Spring Boot application:
```bash
mvn spring-boot
```

## Contributing

Contributions are welcome! If you want to contribute to this project, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature`)
3. Make your changes and commit them (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature`)
5. Create a new Pull Request.

## License

This project is licensed under the MIT License.
