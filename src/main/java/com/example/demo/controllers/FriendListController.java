package com.example.demo.controllers;



import com.example.demo.services.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")

public class FriendListController {

    @Autowired
    FriendListService friendListService;
}