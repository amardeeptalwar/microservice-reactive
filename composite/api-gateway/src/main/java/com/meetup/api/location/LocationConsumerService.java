package com.meetup.api.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rx.Observable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

@Component
public class LocationConsumerService
{
    @Autowired
    LocationConsumer consumer;

    @HystrixCommand(fallbackMethod = "defaultLocationObservable")
    public Observable<Location> getLocationObservable(Integer id)
    {
        return new ObservableResult<Location>()
        {
            @Override
            public Location invoke()
            {
                return consumer.getLocation(id);
            }
        };
    }

    @HystrixCommand(fallbackMethod = "defaultLocationsObservable")
    public Observable<List<Location>> getLocationsObservable()
    {
        return new ObservableResult<List<Location>>()
        {
            @Override
            public List<Location> invoke()
            {
                return consumer.getLocations();
            }
        };
    }

    public Observable<Location> defaultLocationObservable(Integer id)
    {
        System.out.println("defaultLocationObservable");
        return null;
    }

    public Observable<Location> defaultLocationsObservable()
    {
        System.out.println("defaultLocationsObservable");
        return null;
    }

}
