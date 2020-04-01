package com.example.demo.repositories;

import com.example.demo.entities.Friendship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepo extends CrudRepository<Friendship, Integer> {
}
