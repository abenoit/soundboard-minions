package com.soundboard.minions.soundboardminions.model;

public class Sound {

    private String title;
    private Integer resource;
    private Integer Id;
    private Integer avatarId;

    public Sound(Integer resource, String title, Integer id, Integer avatarId) {
        this.title = title;
        this.resource = resource;
        this.Id = id;
        this.avatarId = avatarId;
    }

    public int getAvatarId()
    {
        return this.avatarId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getResource() {
        return resource;
    }

}
