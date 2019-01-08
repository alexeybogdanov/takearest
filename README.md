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

## Restaurant handling 

* List all restaurants (http://localhost:8080/api/restaurants)

    `curl -v http://localhost:8080/api/restaurants -H "Authorization: Basic dXNlcjoxMjM0NQ=="`
 

* Display particular restaurant (http://localhost:8080/api/restaurants/1)

    `curl -v http://localhost:8080/api/restaurants/2 -H "Authorization: Basic dXNlcjoxMjM0NQ=="`
    
* Create restaurant (Only admin allowed)

    `curl -v 'http://localhost:8080/api/restaurants' -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" --header 'Content-Type:    application/json' -X POST  --data '{"name":"CURLrestaurant"}'` 
    
* Find restaurant by name (http://localhost:8080/api/restaurants/search/by-name?name=MacDac)

    `curl -v 'http://localhost:8080/api/restaurants/search/by-name?name=MacDac' -H 'Authorization:Basic YWRtaW46cGFzc3dvcmQ='`    
    
* Modify restaurant (Only admin allowed)

    `curl -v http://localhost:8080/api/restaurants/1 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ="  --header 'Content-type:application/json' -X PUT --data '{"name": "KFC"}'`
     

* Delete restaurant (Only admin allowed)

    `curl -v http://localhost:8080/api/restaurants/2 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" -X DELETE`
   
* Delete restaurant (Check access denied for user)  

    `curl -v http://localhost:8080/api/restaurants/2 -H "Authorization: Basic dXNlcjoxMjM0NQ==" -X DELETE`

## Meal handling

* Create meal for restaurant (Only admin allowed)

    `curl -v http://localhost:8080/api/meals -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" --header 'Content-Type:           application/json' -X POST --data '{"name":"CURLhamburger","price":3.0,"restaurant": {"id":2, "name":"Shawerma"}}'`
  
* Update meal (Only admin allowed)

    `curl -v http://localhost:8080/api/meals/1 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" --header 'Content-type:application/json' -X POST --data '{"name": "NEW_NAME","price":3.5}'`
    
* Update meal (Check access denied for user)

    `curl -v http://localhost:8080/api/meals/1 -H "Authorization: Basic dXNlcjoxMjM0NQ==" --header 'Content-type:application/json' -X POST --data '{"name": "NEW_NAME","price":3.5}'`    
  
  
* Delete meal (Only admin allowed)

    `curl -v http://localhost:8080/api/meals/1 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" -X DELETE`
    
## Vote handling

* Vote for restaurant (Only user allowed)

    `curl -v localhost:8080/api/vote/1 -H "Authorization: Basic dXNlcjoxMjM0NQ==" -X PUT -H 'Content-type:application/json'`
    
* Vote for restaurant (Check access denied for admin)

    `curl -v localhost:8080/api/vote/1 -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ=" -X PUT -H 'Content-type:application/json'`    

* Search vote in history by date (http://localhost:8080/api/vote/search/by-date?date=2018-12-23)

    `curl -v 'http://localhost:8080/api/vote/search/by-date?date=2018-12-23' -H 'Authorization:Basic dXNlcjoxMjM0NQ=='`

