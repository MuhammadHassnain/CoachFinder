# CoachFinder
A spring boot project for coach finding

# Application Features

- View list of available coaches.
- Visit the website, without registering and send requests to coaches.
- Create and Register as a normal user.
- Register as a Coach, after registering as a normal user.
- Send Requests to coaches.
- View the received requests from a normal user as a coach.
- Filter the coaches on the basis of skills



## Backend
### Technology: 

- Spring Boot 2.5.4
- Java Version 11
- MySQL 

### Features
- Spring Security to secure the endpoint.
- Use of JWT to securly communicates between client side and server side.
- Role Base Access Controls i.e. every user doesn't have access to every endpoint

### Endpoints
Endpoint | Method | Usage | Restriction | Role
-------- | ------ | ----- | ----------- | ----
api/login | POST | To login for registeed users and get JWT in response, which contains user information | Public | 
api/signup | POST | To create an account without any role | Public | 
api/coaches/register | POST | To register existing user as a coach | Restricted | Only for Registered Users
api/coaches/profile | GET | To fetch the coach profile | Restricted | Has Role Coach
api/coaches/requestes | GET | To fetch the received request(s) by a users | Restricted | Has Role Coach
api/coaches | GET | Get the list of all registered coaches | Public | 
api/requests | POST | To send the request to a coach | Public | 

**NOTE:**  All the restricted endpoints use JWT to identify the user.

### How to Start the backend

1. Configue the database connection in application.properties file.
2. Use Maven to build the backend.
3. Run CoachFinderApplication.java in src/main/java/com/coachfinder as Java Application.
4. The applcation will start at port 9090.


## Frontend
### Technology 

- Vue JS 3
- Node v12.22.6

### Features

- Login and Logout Page.
- Auto Login on refresh and browser restart.
- Dynamic content based on current state.
  - Replacment of login button with logout on login and vice verse.
  - Add request component, if the logged-in user has Role of Coach
- Vue Router guards to protect the routes. 
- Vuex Store to store the data.
- Use of Vuex to easily transfer data between different components.
- Use the feature of **slots** to create reuseable components, like buttons, dialog box and registration form, Cards etc.
- A page to view and filter coaches based on skills.
- A page to view the requests by coaches, only available when user is coach and logged in. Protected by Vue Router Gurad.
- A page to register as Coach.
- A page to send request to a coach.
- Animations to make the flow of application smooth and interactive. 

### How to Start the frontend

Run the below command in frontend folder, to run the application locally at port 8080

> npm run serve
