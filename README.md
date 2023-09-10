# Time-Wise-API

Time-Wise-API is a Spring Boot application that allows you to manage goals and progress. Below you will find information about configuring the application.

## Configuration

### Mail Server Configuration

The application uses a mail server for sending notifications and emails. Below are the configuration settings for the mail server:

```yaml
mail:
  host: smtp.gmail.com
  port: 587
  username: [ENTER YOUR EMAIL ADDRESS]
  password: [ENTER YOUR EMAIL ACCOUNT PASSWORD]
  properties:
    mail:
      debug: true
    transport:
      protocol: smtp
    smtp:
      auth: true
      starttls:
        enable: true
```
### Database Configuration

The application uses an H2 database. Below are the configuration settings for the database:

```yaml
datasource:
  url: 'jdbc:h2:file:./data/testdb'

jpa:
  hibernate:
    ddl-auto: update
  show-sql: true
  properties:
    hibernate:
      format_sql: true
  database-platform: org.hibernate.dialect.H2Dialect
```

### JWT (JSON Web Tokens) Configuration

The application uses JWT tokens for authentication and access control. Below are the JWT configuration settings:

```yaml
application:
  security:
    jwt:
      secret-key: [ENTER YOUR JWT SECRET KEY]
      expiration: 86400000 # Token validity period (1 day)
      refresh-token:
        expiration: 604800000 # Refresh token validity period (7 days)
```

### Using the Application

After configuring the application according to the instructions above, you can run Time-Wise-API and start using it.

### Authors
- Jakub Popiołek

