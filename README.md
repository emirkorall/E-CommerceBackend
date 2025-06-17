# E-CommerceBackend
# Ecommerce Application

## Configuration Setup

This project uses environment-specific properties files to manage configuration. Follow these steps to set up your local environment:

1. Copy the template file to create your local properties file:
   ```
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```

2. Edit the `application.properties` file to add your actual database credentials and JWT secret key.

3. The actual `application.properties` file is excluded from Git tracking to protect sensitive information.

### Environment Variables

Alternatively, you can set the following environment variables:

- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `JWT_SECRET_KEY` - Secret key for JWT token generation

## Security Note

Never commit files containing sensitive information like passwords or API keys to version control systems.