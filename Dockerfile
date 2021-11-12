FROM	maven:3-jdk-8-alpine as maven

COPY	pom.xml .
RUN		mvn verify clean --fail-never
COPY	src/ ./src/
RUN		mvn package 

FROM	ubuntu:20.04

LABEL	maintainer "Ammar Ammar <ammar257ammar@gmail.com>"

RUN	apt-get update && \
	apt-get install -y wget gnupg libgconf-2-4 xvfb unzip openjdk-8-jdk ant
		
ENV 	JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN 	export JAVA_HOME

RUN 	wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN 	echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list

RUN	apt-get update -y && \
	apt-get install -y google-chrome-stable && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer

COPY	--from=maven target/nsdra-assessment-webapp-0.0.1-SNAPSHOT.jar /app/nsdra-webapp.jar

RUN 	mkdir /etc/bmuse
ENV 	CHROMEDRIVER_VERSION 93.0.4577.15
ENV 	CHROMEDRIVER_DIR /etc/bmuse/driver
RUN 	mkdir $CHROMEDRIVER_DIR

RUN 	wget -q --continue -P $CHROMEDRIVER_DIR "http://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip"
RUN 	unzip $CHROMEDRIVER_DIR/chromedriver* -d $CHROMEDRIVER_DIR

ENV 	PATH $CHROMEDRIVER_DIR:$PATH
RUN 	chmod +x /etc/bmuse/driver/chromedriver

ENTRYPOINT ["java","-jar","/app/nsdra-webapp.jar"]
CMD ["-h"]
