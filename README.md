### JAVA COMMON
> 避免引起jar包版本冲突，建议移除common带的一些包
````xml
<dependency>
            <groupId>com.banggood</groupId>
            <artifactId>common</artifactId>
            <version>1.0.0</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                </exclusion>
            </exclusions>
</dependency>
````