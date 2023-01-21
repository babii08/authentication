# Code challenge

Application that authenticate and authorize customers and allow access based only on jwt token

## Installation

Clone the repository

No additional installation required


## Usage

Open the application inside an ide.
Run the application

After application started successfully try requests against the application using postman

1. POST http://localhost:8080/api/auth/register
```json
{
    "name": "John",
    "email": "john@mail.com",
    "password": "password"
}
```

You will receive a response as json token 
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyaWFuQGVtYWlsLmNvbSIsImlhdCI6MTY3Mzc4ODIxNCwiZXhwIjoxNjczNzg5NjU0fQ.KhW2PNKZWzMsg_skGZZnyry-E_m6PSuW2v5jM2eNGe8"
}
```

2. Try logging in. POST http://localhost:8080/api/auth/authenticate 
```json
{
  "email": "john@mail.com",
  "password": "password"
}
```

If you skip step 1, application should return a 403 error response

3. Now get customer information using 
GET http://localhost:8080/api/info?email=john@email.com

Again, if you skip step 1 or token on step 1 or 2 expired, you will receive 403 on this endpoint.