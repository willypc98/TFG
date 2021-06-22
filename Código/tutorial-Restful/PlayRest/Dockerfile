FROM openjdk:8-oraclelinux7

USER root


RUN curl -L https://www.scala-sbt.org/sbt-rpm.repo > sbt-rpm.repo
RUN mv sbt-rpm.repo /etc/yum.repos.d/
RUN yum install sbt -y

RUN mkdir /home/app
WORKDIR /home/app

COPY . ./

CMD sbt run 