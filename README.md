# RabbitMQTutorial
RabbitMQTutorial Sample Application

Usage: Change spring.profiles.active and start the application

spring:
  profiles:
    active: {type}, {role}
    
Sample:

spring:
  profiles:
    active: {topic}, {sender}
    
types:
1.hello-world
2.worker
3.pub-sub
4.routing
5.topic

roles:
1.sender
2.receiver
