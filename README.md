authorization_code
=====

- http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://localhost:9000/callback&scope=read_profile

- http://localhost:9000/callback?code=rBe22r

- command

   - $ curl -v -X POST http://{client_id}:{client_secret}@localhost:8080/oauth/token -d code={code} -d **`grant_type`**=**`authorization_code`** -d redirect_uri={redirect_uri} -d scope={scope}


- example

	```
	curl -v -X POST http://client:password@localhost:8080/oauth/token -d code=rBe22r -d grant_type=authorization_code -d redirect_uri=http://localhost:9000/callback -d scope=read_profile
	```


implicit
=====

- http://localhost:8080/oauth/authorize?response_type=token&client_id=client&redirect_uri=http://localhost:9000/callback&scope=read_profile&state=test

- http://localhost:9000/callback#access_token=KpzuGBLYqXGR0RhjgrVyPubOkvA&token_type=bearer&state=test&expires_in=119


password
=====

- command

   - $ curl -v -X POST http://{client_id}:{client_secret}@localhost:8080/oauth/token -d **`grant_type`**=**`password`** -d username={username} -d password={password} -d scope={scope}

- example

	```
	curl -v -X POST http://client:password@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pass -d scope=read_profile
	```

#### REQUEST HEADER

- header
```
Content-Type - Content type of the request body: application/x-www-form-urlencoded
Authorization - Client credential for HTTP Basic authentication scheme (i.e., base64(client_id:client_secret)).
```

#### RESPONSE BODY

- json
```
{
   "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQlJBTkRfUkVTT1VSQ0UiXSwiZXhwIjoxNTU4MDU5MTYwLCJ1c2VyX25hbWUiOiJicmFuZDAxIiwiYXV0aG9yaXRpZXMiOlsidXBkYXRlX2JyYW5kIiwiY3JlYXRlX2JyYW5kIiwiZGVsZXRlX3RlbXBsYXRlIiwiY3JlYXRlX3RlbXBsYXRlIiwic2VsZWN0X2JyYW5kIiwicm9sZV9tZXNzYWdlIiwic2VsZWN0X2NoYXRib3QiLCJjcmVhdGVfY2hhdGJvdCIsInJvbGVfYnJhbmQiLCJyb2xlX3RlbXBsYXRlIiwicm9sZV9jaGF0Ym90IiwidXBkYXRlX2NoYXRib3QiLCJ1cGRhdGVfdGVtcGxhdGUiLCJjcmVhdGVfbWVzc2FnZSIsInNlbGVjdF90ZW1wbGF0ZSIsImRlbGV0ZV9icmFuZCIsImRlbGV0ZV9jaGF0Ym90Il0sImp0aSI6IjBkZGFlMDE1LTI3MGMtNGY1Zi1hN2JhLWY3MzdkMDNiMzA0NyIsImNsaWVudF9pZCI6ImJyYW5kX3BvcnRhbCJ9.EVAnI_nwV153zemTS7Lrss24OtVtJy3-g2MVeTRr-nmxaISTZhiri4fP_YN7TKfsdeEOzdFoaRAO4z-bsVvRM-0FhoqcMr0axVCC3aQlbw04be91VadRcinX-Ti5Mw2eM8VxGf4KX-Gu8YTZjz3mystFPZFkFI2J1ukN_vprwS0d9JZb2O-fUWNH5vLB7dnOCJcjXWUQ7RX_osSNwiWkTdtalaTwf6Z07DP3_wHoABsrD5fPxmYyZbgvha7zY2-6GrCqf1IBIv5cHyBqIimHS3YnJrBLmE1rfu5a7zbwE6ji3tOw9IgLBftjXFzaatNOT0iu4UphtFrd5tQPBvQ_jg",
   "token_type": "bearer",
   "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiQlJBTkRfUkVTT1VSQ0UiXSwidXNlcl9uYW1lIjoiYnJhbmQwMSIsImF0aSI6IjBkZGFlMDE1LTI3MGMtNGY1Zi1hN2JhLWY3MzdkMDNiMzA0NyIsImV4cCI6MTU1OTI2ODE2MCwiYXV0aG9yaXRpZXMiOlsidXBkYXRlX2JyYW5kIiwiY3JlYXRlX2JyYW5kIiwiZGVsZXRlX3RlbXBsYXRlIiwiY3JlYXRlX3RlbXBsYXRlIiwic2VsZWN0X2JyYW5kIiwicm9sZV9tZXNzYWdlIiwic2VsZWN0X2NoYXRib3QiLCJjcmVhdGVfY2hhdGJvdCIsInJvbGVfYnJhbmQiLCJyb2xlX3RlbXBsYXRlIiwicm9sZV9jaGF0Ym90IiwidXBkYXRlX2NoYXRib3QiLCJ1cGRhdGVfdGVtcGxhdGUiLCJjcmVhdGVfbWVzc2FnZSIsInNlbGVjdF90ZW1wbGF0ZSIsImRlbGV0ZV9icmFuZCIsImRlbGV0ZV9jaGF0Ym90Il0sImp0aSI6IjE4ZTFmMmI5LTNiOTktNGZkOC04MzJlLTE5OGZkODA2ZjNiZiIsImNsaWVudF9pZCI6ImJyYW5kX3BvcnRhbCJ9.UgPXVjvO7X0P-_hyaCwINvx7due1dS2GZErieDnfjtmwn8ZoFBa5DNLnl2cHkGqUpogCvmhuqp7DazCT1mkD2jixkoJaCsbNc3ZFenmaJhxe8UaHKdTRom0spLC82qLsCqvEyxkL8evK2mZWUnDuudiML7fFpJmT6QVgCGVZ0QOsJ-w8CTAALQTiBFuc9W8RthVpZ0mpMwZ16gXrLDdrL2nmaKWSLxUITFaJaZ0FqPmmcmkFlIVkOOcO5NO6CTHdP-qB7We9Dk_ul1fUfUactMXLN8nxCi1sAxkztE4DYy8dYCmlTGHS0N9hNZO201vQZSol9qiIx0LFYnlRUCU4Hw",
   "expires_in": 599
}
```

#### RESPONSE STATUS

- 200 OK
```
"access_token": The access token issued by the authorization server.
"token_type": The type of the token issued.
"expires_in": The lifetime in seconds of the access token.
"refresh_token": The refresh token, which can be used to obtain new access tokens using the same authorization grant.
```

- 400 Bad Request
```
The request is invalid. Note that Authroization requires token_type.
```

- 401 Unauthorized
```
The authentication is failed.
```

- 404 Not Found
```
The client_id cannot be found. Application shall check its client_id is correct.
```

- 500 Server Internal Error
```
Temporary internal server error.
```

- 502 Bad Gateway
```
Temporary internal server connection error.
```


client_credentials
=====

- command

   - $ curl -v -X POST http://{client_id}:{client_secret}@localhost:8080/oauth/token -d **`grant_type`**=**`client_credentials`** -d scope={scope}

- example

	```
	curl -v -X POST http://client:password@localhost:8080/oauth/token -d grant_type=client_credentials -d scope=read_profile
	```


refresh_token
=====

- command

   - $ curl -v -X POST http://{client_id}:{client_secret}@localhost:8080/oauth/token -d **`grant_type`**=**`refresh_token`** -d refresh_token={refresh_token}

- example

	```
	curl -v -X POST http://client:password@localhost:8080/oauth/token -d grant_type=refresh_token -d refresh_token=eyJhbGciOiJSUzI1Ni..
	```
