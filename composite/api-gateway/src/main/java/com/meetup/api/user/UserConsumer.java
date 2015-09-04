package com.meetup.api.user;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("USER-SERVICE")
public interface UserConsumer
{
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers();

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id);
}
