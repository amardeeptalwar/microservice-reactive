package com.meetup.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GroupServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GroupServiceApplication.class, args);
    }

}
