package com.meetup.api.group;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("GROUP-SERVICE")
public interface GroupConsumer
{
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Group> getGroups();

    @RequestMapping( value = "/group/{id}" , method = RequestMethod.GET)
    public Group getGroup(@PathVariable("id") Integer id);
}
