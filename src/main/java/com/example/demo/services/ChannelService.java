package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.repositories.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    ChannelRepo channelRepo;

    public List<Channel> findAllChannels(){
        return (List<Channel>) channelRepo.findAll();
    }

    public Channel findOneChannel(int id){
        Channel channel = channelRepo.findById(id);
        if(channel == null) return null;
        return channel;
    }

    public List<Channel> findAllByAdminId(int adminId){
        return channelRepo.findAllByAdminId(adminId);
    }

    public Channel findByNameIgnoreCase(String name){
        Channel channel = channelRepo.findByNameIgnoreCase(name);
        if(channel == null) return null;
        return channel;
    }

    public Channel createNewChannel(Channel newChannel){
        return channelRepo.save(newChannel);
    }


}
