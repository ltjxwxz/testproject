package com.wxx.thymeleaftest.dao;

import com.wxx.thymeleaftest.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wxx on 2017/11/12.
 */
@Repository("userDao")
public class UserDao {

    private AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();

    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id == null) {
            // 如果为空，是保存，不为空，是更新
            id =  counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    public void deleteUser(Long id) {
        this.userMap.remove(id);
    }

    public User getUserById(Long id) {
        return this.userMap.get(id);
    }

    public List<User> listUsers() {
        return new ArrayList<User>(this.userMap.values());
    }
}
