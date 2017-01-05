# Basic Akka HTTP service deployable on Heroku with sbt-heroku

## Requirements
This is an application that is built and deployed via sbt, and thus
requires a working installation of sbt.

In order to deploy this application to Heroku, you need a Heroku account, 
as well a created Heroku application, i.e. you ran 
```bash
heroku create -n
```
with the Heroku CLI.

## How to deploy
```bash
sbt -Dheroku_name=<HEROKU_NAME> clean compile stage deployHeroku
```

Note: deploying via git (`git push heroku master`) will *BREAK* this application. 
