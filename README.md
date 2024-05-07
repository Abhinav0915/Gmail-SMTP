# Email Service Application

This is a simple web application for sending emails, developed with a backend built using Spring Boot and a frontend built using React.js.

## Endpoints

The backend provides the following endpoints:

- **POST `/sendMail`**: Sends an email without attachment.
- **POST `/sendAttachmentMail`**: Sends an email with attachment.

## Dependencies Used

### Backend Dependencies:
- **Spring Boot**: Framework for creating web applications with Java.
- **Spring Boot Starter Mail**: Provides support for sending emails in Spring Boot applications.
- **Lombok**: Java library to reduce boilerplate code.
- **JUnit**: Testing framework for Java.

### Frontend Dependencies:
- **React.js**: JavaScript library for building user interfaces.
- **react-scripts**: Configuration and scripts for Create React App.

## How to Run the Application

### Backend Setup:
1. Ensure you have Maven installed.
2. Navigate to the `EmailService-Backend` directory.
3. Run the following command to start the backend server:

```bash
mvn spring-boot:run
```
### Frontend Setup:
1. Ensure you have Node.js and npm installed.
2. Navigate to the frontend directory.
3. Run the following command to install dependencies:

```bash
npm install
```
4. Run the following command to start the frontend server:

```bash
npm run dev
```

### Accessing the Application:
- Open your web browser and go to `http://localhost:5173` to access the frontend application.
- Interact with the frontend UI to send emails with or without attachments.

## Sample Usage:
1. **Sending Email without Attachment:**
- Enter recipient email, subject, and message body.
- Click on the "Send Email" button.

2. **Sending Email with Attachment:**
- Enter recipient email, subject, message body, and choose an attachment file.
- Click on the "Send Email" button.
