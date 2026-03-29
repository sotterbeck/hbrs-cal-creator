# Agent Instructions for H-BRS Cal Creator

This is a monorepo with a **Kotlin/Spring Boot backend** and a **Next.js 15/React 19 frontend**.

## Project Structure

```
├── backend/               # Spring Boot Kotlin application
│   ├── src/main/kotlin/   # Production code
│   └── src/test/kotlin/   # Test code
├── frontend/              # Next.js 15 application
│   ├── app/               # Next.js App Router pages
│   ├── components/        # React components (ui/, teachingEvents/, semesters/)
│   └── lib/               # Utilities and API hooks
├── gradle/                # Gradle configuration
└── docker-compose.yml     # Docker setup
```

## Build/Lint/Test Commands

### Backend (Kotlin/Gradle)

```bash
# Run all tests
./gradlew test

# Build the application
./gradlew build

# Run the backend (development)
./gradlew bootRun

# Clean build
./gradlew clean
```

### Frontend (Next.js)

```bash
# Install dependencies
cd frontend && npm install

# Format code with Prettier
cd frontend && npm run prettier

# Start production server
cd frontend && npm start
```

## Code Style Guidelines

### Kotlin Backend

- **Formatting**: Kotlin uses standard formatting with 4-space indentation
- **Naming**:
    - Classes: `PascalCase` (e.g., `TeachingEventController`)
    - Functions/properties: `camelCase` (e.g., `getAllTeachingEventsInteractor`)
    - Interfaces: `PascalCase` with `*able` suffix when applicable
    - Test classes: Same as production classes with `Test` suffix
- **Architecture**: Clean Architecture with clear separation
    - Controllers handle HTTP requests
    - Interactors contain business logic
    - Repositories handle data access
    - Presenters format output
- **Error Handling**: Use Kotlin's Result type or return null/empty for expected failures
- **Coroutines**: Use `suspend` functions with `runBlocking` in tests
- **Testing**: Use JUnit 5 with assertk for assertions and mockk for mocking

### React/TypeScript Frontend

- **Formatting**: Prettier with Vercel style guide + Tailwind plugin
- **TypeScript**: Strict mode enabled; always define prop types
- **Naming**:
    - Components: `PascalCase` (e.g., `TeachingEvents.tsx`)
    - Files: `kebab-case` (e.g., `teaching-events.tsx`)
    - Hooks: `camelCase` with `use` prefix (e.g., `useTeachingEventSearching`)
    - Utils: `camelCase` (e.g., `calendar.utils.ts`)
- **Components**:
    - Use `'use client'` directive for client components
    - Prefer `React.FC` or `React.ComponentType` for component typing
    - Use `Readonly<>` for immutable props
    - Use `forwardRef` when ref forwarding is needed
- **Styling**: Tailwind CSS with CSS variables for theming
    - Use shadcn/ui component patterns
    - Use `cn()` utility for merging classNames
- **State**: Prefer React hooks (`useState`, `useEffect`) over external state management
- **Imports**: Use `@/` path alias for project imports

### General

- Run `npm run prettier` before committing frontend changes
- Run `./gradlew test` before committing backend changes
- Keep functions small and focused
- Write tests for new functionality
- Use meaningful, descriptive names
- Document complex business logic with comments

## Tech Stack

### Backend

- Kotlin
- Spring Boot (WebFlux)
- Reactor for reactive programming
- Apache POI for Excel parsing
- SkrapeIt for HTML parsing
- Biweekly for iCal generation
- JUnit 5 + assertk + mockk for testing

### Frontend

- Next.js 15 (App Router, Turbopack)
- React 19
- TypeScript 5 (strict mode)
- shadcn/ui
