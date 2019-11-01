#################################################################
# Creates a docker image
#                    ##        .
#              ## ## ##       ==
#           ## ## ## ##      ===
#       /""""""""""""""""\___/ ===
#  ~~~ {~~ ~~~~ ~~~ ~~~~ ~~ ~ /  ===- ~~~
#       \______ o          __/
#         \    \        __/
#          \____\______/
# Author: Andrei Belov <andrei_belov@epam.com>
# License:      MIT
# Version:		v0.1.1
#################################################################
FROM java:8u111-jre-alpine

ARG JAR_FILE='backend-0.1.0.jar'

COPY ${JAR_FILE} /usr/local/apps/backend.jar
EXPOSE 8080 9090 80
ENTRYPOINT ["java","-jar","/usr/local/apps/backend.jar"]

