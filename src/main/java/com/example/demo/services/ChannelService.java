package com.example.demo.services;

import com.example.demo.entities.Channel;
import com.example.demo.entities.DeleteClass;
import com.example.demo.repositories.ChannelRepo;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    ChannelRepo channelRepo;
    @Autowired
    private SocketService socketService;

    public List<Channel> findAllChannels(){
        return (List<Channel>) channelRepo.findAll();
    }

    public Channel findOneChannel(int id){
        Channel channel = channelRepo.findById(id);
        if(channel == null) return null;
        return channel;
    }

    public List<Channel> findAllByAdminId(int adminId){
        return channelRepo.findAllByAdminid(adminId);
    }

    public Channel findByNameIgnoreCase(String name){
        Channel channel = channelRepo.findByNameIgnoreCase(name);
        if(channel == null) return null;
        return channel;
    }

    public Channel createNewChannel(Channel newChannel){
        Channel channel = null;

        try{
            channel = channelRepo.save(newChannel);

            channel.action = "new-channel";

            socketService.sendToAll(channel, Channel.class);
        } catch(Exception e){
            e.printStackTrace();
        }

        return channel;
    }

    public Channel changeChannelName(Channel idAndName){
        Channel channel = null;

        try{
            channel = channelRepo.save(idAndName);

            channel.action = "change-channel-name";

            socketService.sendToAll(channel, Channel.class);
        } catch(Exception e){
            e.printStackTrace();
        }

        return channel;
    }

    public void deleteChannel(int id){
        DeleteClass deleteChannel = new DeleteClass();

        try{
            deleteChannel.setIndex(id);
            deleteChannel.setAction("delete-channel");
            channelRepo.deleteById(id);
            socketService.sendToAll(deleteChannel, DeleteClass.class);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
