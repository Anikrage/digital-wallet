💸 Digital Wallet & Fund Transfer System

A robust, production-ready backend for digital wallets—built with Spring Boot, PostgreSQL, Docker, and Maven.
Seamlessly supports peer-to-peer transfers, atomic transactions, real-time balance checks, and secure RESTful APIs.
🚀 Features

Instant Peer-to-Peer Transfers: Move funds between wallets with ACID-compliant safety.

Atomic Transactions: No lost money, even under heavy concurrency.

Comprehensive API Suite: Create users, open wallets, transfer funds, check balances, and view transaction history.

Event-Driven Architecture: Ready for Kafka integration for real-time notifications and scalable microservices.

Production-Grade Setup: Containerized with Docker Compose for easy local or cloud deployment.

Swagger/OpenAPI Docs: Explore and test APIs interactively.

Built-in Validation & Error Handling: Secure, predictable, and developer-friendly.

🛠️ Tech Stack

Backend: Java 17, Spring Boot 3
Database: PostgreSQL
Build: Maven
Containerization: Docker & Docker Compose
API Docs: Swagger / OpenAPI

⚡ Quickstart

Clone the repo
git clone https://github.com/Anikrage/digital-wallet.git

cd digital-wallet

Build & run with Docker Compose

docker compose up --build

Access API docs
open http://localhost:8080/swagger-ui.html

📚 API Highlights

POST /api/users — Create a user

POST /api/wallets/create — Create a wallet for a user

POST /api/wallets/{walletId}/add — Add money to a wallet

POST /api/wallets/transfer — Peer-to-peer transfer

GET /api/wallets/{walletId}/balance — Check wallet balance

GET /api/wallets/{walletId}/transactions — View transaction history

🧩 How It Works

ACID Transactions: All fund transfers are atomic and consistent, even with simultaneous requests.

Concurrency Control: Optimistic and pessimistic locking strategies prevent race conditions.

Input Validation: Ensures only valid, secure data enters the system.

Easy Integration: RESTful APIs and Swagger docs make it simple to connect web, mobile, or other services.

📦 Project Structure

text
src/
 ├── main/
 │    ├── java/com/digitalwallet/...
 │    └── resources/application.properties
 └── test/
      └── java/com/digitalwallet/...
Dockerfile
docker-compose.yml
pom.xml

🏆 Why This Project?

Built for real-world financial platforms, this backend demonstrates scalable architecture, clean code, and production best-practices—ready for your next fintech innovation!

Ready to build the next generation of digital payments? Fork, clone, and launch!

Made with ☕ and 💡 by [Anikrage]
