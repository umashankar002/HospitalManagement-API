To run this application, please follow the steps outlined below. Additionally, there are several endpoints available that can be tested using a tool like Postman.

Step-1:Configure MySQL Workbench
		-Create a new schema by executing the following SQL query
		-CREATE SCHEMA hospital_db;

Step-2:Update application.properties file in your Spring Boot project
		-spring.datasource.username=root
		spring.datasource.password=password

Step-3:Run the Application:
		-Make sure you have the necessary dependencies and configurations in your project (e.g., Spring Security, JWT).
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Sign Up:
Method: POST
Endpoint: http://localhost:8080/api/auth/signup
Request Body:
{
  "name": "Karl",
  "username": "karl",
  "email": "karl@gmail.com",
  "password": "12345"
}
Description: This endpoint is used to create a new user account. The user provides their name, username, email, and password in the request body. Upon successful registration, the user will be able to authenticate and access protected resources.


Sign In:
Method: POST
Endpoint: http://localhost:8080/api/auth/signin
Request Body:
{
  "usernameOrEmail": "karl",
  "password": "1234655"
}
Description: This endpoint is used for user authentication. The user provides their username or email and password in the request body. If the provided credentials are valid, a JSON Web Token (JWT) will be returned, which can be used to authorize subsequent requests.

Create a Patient:
Method: POST
Endpoint: http://localhost:8080/api/patients
Request Body:
{
  "name": "Abc",
  "age": 42,
  "room": "516",
  "doctorName": "Dr. L K",
  "admitDate": "2032-05-30",
  "expenses": 7890.0,
  "status": "admitted"
}
Description: This endpoint is used to create a new patient record. The request body contains various details of the patient, such as name, age, room number, doctor's name, admit date, expenses, and status.

Get Patients:
Method: GET
Endpoint: http://localhost:8080/api/patients
Description: This endpoint retrieves a list of all patients. It returns an array of patient objects containing their respective details, such as name, age, room number, doctor's name, admit date, expenses, and status.

Authentication and Authorization:
This project utilizes Spring Security and JWT for authentication and authorization purposes. When a user signs up or signs in, their credentials are validated, and a JWT token is generated and returned. This token should be included in the Authorization header of subsequent requests as a bearer token to access protected endpoints. The Spring Security configuration ensures that only authenticated users with valid JWT tokens can access the protected resources.

It's important to note that the provided documentation assumes the project is running locally on http://localhost:8080. Please adjust the base URL accordingly if the application is hosted on a different domain or port.