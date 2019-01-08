## Test task
### Voting System

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

### Install and run

git clone https://github.com/alexeybogdanov/takearest

$ mvn spring-boot:run (from project directory)

### Users
* admin/password 

Authorization: Basic YWRtaW46cGFzc3dvcmQ=

* user/12345

Authorization: Basic dXNlcjoxMjM0NQ==

### H2 DB access
http://localhost:8080/h2

* username: sa
* without password

### Restaurant handling 

* List all restaurants

    curl http://localhost:8080/api/restaurants -H "Authorization: Basic dXNlcjoxMjM0NQ==" 
 

* Display particular restaurant

    curl http://localhost:8080/api/restaurants/2 -H "Authorization: Basic dXNlcjoxMjM0NQ==" 
    
* Create restaurant (Only admin allowed)

    curl 'http://localhost:8080/api/restaurants' -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" --header "Content-Type:             application/json" --request POST  --data '{"name":"CURLrestaurant"}' 

* Delete restaurant (Only admin allowed)
    '''curl http://localhost:8080/api/restaurants/1 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" -X DELETE''' 

