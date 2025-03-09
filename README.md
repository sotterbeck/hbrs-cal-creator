# H-BRS Cal Creator

A web application to create an iCal file for selected courses at H-BRS. This is useful for students who want to import
their courses into their calendar application, without having to manually enter each course manually.

> **Note:** This project is not affiliated with Hochschule Bonn-Rhein-Sieg. It is a private project by students for
> students.

## Features

- Simple and intuitive user interface
- 1:1 conversion of teaching events to iCal (respecting odd and even weeks)
- Completely free and open-source, possible to self-host on your own server

A public instance is available at https://calcreator.sotterbeck.de/.

## Development

1. Clone the repository
    ```bash
    git clone https://github.com/sotterbeck/hbrs-cal-creator.git
    ```
2. Run the backend
   ```bash
   ./gradlew bootRun
   ```
3. Start the frontend development server
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

4. Open your browser and navigate to `http://localhost:3000/` to access the application.

### Self-Hosting

You can use Docker to run the application. The backend and frontend are separate docker containers. It is recommended to
use Docker Compose.

Example `docker-compose.yml` file:

```yaml
version: '3.8'

services:
   backend:
      image: ghcr.io/sotterbeck/hbrs-cal-creator-backend:latest
      container_name: hbrs-cal-creator-backend
      networks:
         - app-network
      restart: unless-stopped

   frontend:
      image: ghcr.io/sotterbeck/hbrs-cal-creator-frontend:latest
      container_name: hbrs-cal-creator-frontend
      environment:
         API_URL: http://hbrs-cal-creator-backend:8080
      networks:
         - app-network
      restart: unless-stopped

networks:
   app-network:
      driver: bridge
```

This is a minimal example. In production, you should use a reverse proxy like Nginx or Traefik to handle SSL termination
and routing.