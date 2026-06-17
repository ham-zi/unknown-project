package snippet;

public class Snippet {
	dependencies {
		implementation 'org.springframework.boot:spring-boot-starter-jdbc'
		implementation 'org.springframework.boot:spring-boot-starter-security'
		implementation 'org.springframework.boot:spring-boot-starter-validation'
		implementation 'org.springframework.boot:spring-boot-starter-web'
		implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.5'
		implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.16'
		implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
		implementation 'io.jsonwebtoken:jjwt-jackson:0.12.3'
		implementation 'io.jsonwebtoken:jjwt-impl:0.12.3'
		compileOnly 'org.projectlombok:lombok'
		runtimeOnly 'com.oracle.database.jdbc:ojdbc11'
		annotationProcessor 'org.projectlombok:lombok'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'
		testImplementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.5'
		testImplementation 'org.springframework.security:spring-security-test'
		testCompileOnly 'org.projectlombok:lombok'
		testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
		testAnnotationProcessor 'org.projectlombok:lombok'
	}
}

