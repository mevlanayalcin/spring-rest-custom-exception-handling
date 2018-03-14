package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAO
{
    private static List<User> users=new ArrayList<>();
    private static int userId=3;

    static
    {
        users.add(new User(1,"Mevlana",new Date()));
        users.add(new User(2,"Ahmet",new Date()));
        users.add(new User(3,"Emre",new Date()));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User save(User user)
    {
        if(user.getId()==null)
        {
            user.setId(++userId);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id)
    {
        for(User user:users)
        {
            if(user.getId()==id)
            {
                return user;
            }
        }
        return null;
    }


}
