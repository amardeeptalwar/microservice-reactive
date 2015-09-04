package com.meetup.api.recommendation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rx.Observable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

@Component
public class RecommendationConsumerService
{
    @Autowired
    RecommendationConsumer consumer;

    @HystrixCommand(fallbackMethod = "defaultRecommendationObservable")
    public Observable<Recommendation> getRecommendationObservable(Integer id)
    {
        return new ObservableResult<Recommendation>()
        {
            @Override
            public Recommendation invoke()
            {
                return consumer.getRecommendation(id);
            }
        };
    }

    @HystrixCommand(fallbackMethod = "defaultRecommendationsObservable")
    public Observable<List<Recommendation>> getRecommendationsObservable()
    {
        return new ObservableResult<List<Recommendation>>()
        {
            @Override
            public List<Recommendation> invoke()
            {
                return consumer.getRecommendations();
            }
        };
    }

    public Observable<Recommendation> defaultRecommendationObservable(Integer id)
    {
        System.out.println("defaultRecommendationObservable");
        return null;
    }

    public Observable<Recommendation> defaultRecommendationsObservable()
    {
        System.out.println("defaultRecommendationsObservable");
        return null;
    }

}
