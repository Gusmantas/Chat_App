package com.example.demo.repositories;

import com.example.demo.entities.Channel;
import com.example.demo.entities.UserChannel;
import com.example.demo.services.UserChannelsId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChannelRepo extends CrudRepository<UserChannel, UserChannelsId> {

    // As for now, this repo isn't needed anymore. I'll keep it here, though
    //it might come in handy later.
}
