# Smart Election Management and Voter Information System

This project contains a full-stack election management application built with Java Spring Boot and React.

## Included

- Spring Boot backend with REST APIs for elections and candidates
- React frontend dashboard for election insights
- Embedded H2 database for local development
- Bootstrap data to make the app work immediately

## Run the full application

From the workspace root, run:

```bat
start-all.bat
```

Then open:

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080/api/dashboard

## Backend

```bat
cd backend
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
.
\mvnw.cmd spring-boot:run
```

## Frontend

```bat
cd frontend
npm install
npm run dev -- --host 0.0.0.0
```

## Main modules

- Candidate management API: /api/candidates
- Election management API: /api/elections
- Dashboard summary: /api/dashboard

## Deploy to Render

The root `Dockerfile` builds the React frontend and Spring Boot backend into one web service. Push the project to GitHub, then in Render choose **New + > Blueprint** and connect this repository. Render will read `render.yaml` and create the `election-portal` web service.

Render provides managed PostgreSQL, not managed MySQL. This project can connect to MySQL when you provide a database from an external MySQL host. Set these environment variables for the Render service:

- `DB_URL`: JDBC URL, for example `jdbc:mysql://<host>:3306/aimlah?useSSL=true&serverTimezone=UTC`
- `DB_USERNAME`: database username
- `DB_PASSWORD`: database password
- `DDL_AUTO`: `update`

Use the exact host, database name, and connection options supplied by your database provider. Do not commit database credentials to GitHub. The local default remains an in-memory H2 database.
