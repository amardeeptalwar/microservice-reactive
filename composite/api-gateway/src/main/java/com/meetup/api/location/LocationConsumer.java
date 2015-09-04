package com.meetup.api.location;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("LOCATION-SERVICE")
public interface LocationConsumer
{
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public List<Location> getLocations();

    @RequestMapping( value = "/location/{id}" , method = RequestMethod.GET)
    public Location getLocation(@PathVariable("id") Integer id);
}
