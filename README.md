MicroserviceApp Informations

The following servers should be running
*Mysql
  mysql.server start
*RabbitMq
  /opt/homebrew/opt/rabbitmq/sbin/rabbitmq-server
  How to install RabbitMq:
    brew update
    brew install rabbitmq 
*MongoDb
  brew services start mongodb-community
  How to install MongoDb:
    brew tap mongodb/brew
    brew install mongodb-community

Eureka server : http://localhost:8761/
