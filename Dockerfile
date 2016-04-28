FROM heroku/jvm

COPY ["*.gradle", "gradlew", "*.properties", "/app/user/"]
COPY ["gradle/wrapper/*", "/app/user/gradle/wrapper/"]

RUN chmod +x ./gradlew
RUN ./gradlew dependencies

COPY "src/" "/app/user/src"

RUN ./gradlew stage
