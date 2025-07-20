# GitHub Repository Lister

## Description
Java 21 + Spring Boot 3.5 REST API that lists public, non-fork GitHub repositories for a given user, along with their branches and last commit SHAs.

## Requirements
- Java 21
- Maven

## Running
```bash
mvn spring-boot:run
```

## Example Requests

### Valid user
```bash
curl http://localhost:8080/repositories/octocat
```

### Invalid user
```bash
curl http://localhost:8080/repositories/non_existing_user_xyz
```

Response:
```json
{
  "status": 404,
  "message": "User 'non_existing_user_xyz' not found"
}
```

## Testing
```bash
mvn test
```
