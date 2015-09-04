package com.meetup.api.recommendation;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("RECOMMENDATION-SERVICE")
public interface RecommendationConsumer
{
    @RequestMapping(value = "/recommendations", method = RequestMethod.GET)
    public List<Recommendation> getRecommendations();

    @RequestMapping( value = "/recommendation/{id}" , method = RequestMethod.GET)
    public Recommendation getRecommendation(@PathVariable("id") Integer id);
}
