package com.example.demo.services;

import com.example.demo.repositories.ServerMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerMessageService {

    @Autowired
    ServerMessageRepo serverMessageRepo;


}
