plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.kanduit"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation(files("libs/nes-client-1.6.1.jar")) // Naver Cloud Outbound Mailer SDK
    compileOnly("org.projectlombok:lombok")
    compileOnly("com.squareup.okhttp3:okhttp") // For Naver Cloud Outbound Mailer SDK
    compileOnly("com.squareup.okhttp3:logging-interceptor") // For Naver Cloud Outbound Mailer SDK
    runtimeOnly("commons-codec:commons-codec") // For Naver Cloud Outbound Mailer SDK
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
