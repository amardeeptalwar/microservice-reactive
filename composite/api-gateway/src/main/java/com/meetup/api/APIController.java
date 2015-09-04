package com.meetup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

import com.meetup.api.group.Group;
import com.meetup.api.group.GroupConsumerService;
import com.meetup.api.location.Location;
import com.meetup.api.location.LocationConsumerService;
import com.meetup.api.recommendation.Recommendation;
import com.meetup.api.recommendation.RecommendationConsumerService;
import com.meetup.api.user.User;
import com.meetup.api.user.UserConsumerService;

@RestController
public class APIController
{
    @Autowired
    GroupConsumerService groupConsumerService;

    @Autowired
    UserConsumerService userConsumerService;

    @Autowired
    RecommendationConsumerService recommendationConsumerService;

    @Autowired
    LocationConsumerService locationConsumerService;

    @RequestMapping(method = RequestMethod.GET, value = "/userGroups")
    public UserGroup getUserGroups()
    {
        Observable<List<Group>> groups = groupConsumerService.getGroupsObservable();
        Observable<List<User>> users = userConsumerService.getUsersObservable();

        Observable<UserGroup> userGroupObservable = Observable.zip(groups, users, (g, u) -> {
            return new UserGroup(u, g);
        });

        return userGroupObservable.toList().toBlocking().single().get(0);

    }

    @RequestMapping("/{userId}/{groupId}/userGroup")
    public UserGroup getUserGroup(@PathVariable("userId") Integer userId, @PathVariable("groupId") Integer groupId)
    {
        Observable<Group> groups = groupConsumerService.getGroupObservable(groupId);
        Observable<User> users = userConsumerService.getUserObservable(userId);

        Observable<UserGroup> userGroupObservable = Observable.zip(groups, users, (g, u) -> {
            return new UserGroup(u, g);
        });

        return userGroupObservable.toList().toBlocking().single().get(0);
    }

    @RequestMapping("/{userId}/recommendation")
    public List<Recommendation> getRecommendation(@PathVariable("userId") Integer userId)
    {

        Observable<User> userObservable = userConsumerService.getUserObservable(userId);

        Observable<Location> locationObservable = userObservable.map(user -> user.getId()).flatMap(
                userKey -> locationConsumerService.getLocationObservable(userKey));

        Observable<Recommendation> recommendationObservable = locationObservable.map(location -> location.getId())
                .flatMap(locationId -> recommendationConsumerService.getRecommendationObservable(locationId));

        return recommendationObservable.toList().toBlocking().single();
    }
}
