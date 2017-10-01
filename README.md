# DB Tools for Socialpy

Sometimes, it is better to examine the SQL backend information directly. In this
example, it is displaying all registered users and all their comments from my
Django [Socialpy](https://github.com/kevin-chen-15/socialpy) project.

# Instructions

## Java
+ Install sqlite-jdbc jar file
+ Open and place the code in Eclipse
+ Go to Project > Java Build Path
+ Click add External JARs and go to where sqlite-jdbc is stored, select it, and press OK
+ Click Apply and Close
+ You are now able to run the code in Eclipse

## Python
+ `python sqlitereader.py ./db.sqlite3`
