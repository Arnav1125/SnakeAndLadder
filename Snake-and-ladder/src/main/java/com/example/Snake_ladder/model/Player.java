package com.example.Snake_ladder.model;

import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int position;

   
    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.position = 0; 
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
