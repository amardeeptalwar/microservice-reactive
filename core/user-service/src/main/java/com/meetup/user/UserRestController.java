package com.meetup.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController
{
    private static final List<User> users = new ArrayList<User>();

    static
    {
        users.add(new User(1, "Amardeep", "Singh", "Talwar"));
        users.add(new User(2, "Dhruva", "B", "Bhaskra"));
        users.add(new User(3, "Kartik", "P", "Pandya"));
        users.add(new User(4, "Ranjith", "", "Krishnan"));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getUsers()
    {
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public User getUser(@PathVariable("id") Integer id)
    {
        return users.stream().filter(g -> g.getId() == id).collect(Collectors.toList()).get(0);
    }

}
