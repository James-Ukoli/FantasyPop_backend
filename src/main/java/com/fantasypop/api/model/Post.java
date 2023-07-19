package com.fantasypop.api.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Post {
    //Fields
    private int id;
    private String username; // can be anonymous
    private String content;
    private int voteRatio;
    private int totalVotes;
    private LocalDateTime timestamp;
    private Map<String, String> keyPlayers;
    private String sport;
    private int flagCount;
    private String topic;
    private String comments;

    //Constructor
    public Post(int id, String username, String content, int voteRatio, int totalVotes, LocalDateTime timestamp, Map<String, String> keyPlayers, String sport, int flagCount, String topic, String comments) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.voteRatio = voteRatio;
        this.totalVotes = totalVotes;
        this.timestamp = timestamp;
        this.keyPlayers = keyPlayers;
        this.sport = sport;
        this.flagCount = flagCount;
        this.topic = topic;
        this.comments = comments;
    }

    //Methods get set
    public int getID(){
        return id;
    }
    public void setID(int id) { this.id = id;}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getVoteRatio(){
        return voteRatio;
    }
    public void setVoteRatio(int voteRatio){
        this.voteRatio = voteRatio;
    }
    public int getTotalVotes(){
        return totalVotes;
    }
    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public void setTimeStamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }
    public Map<String, String> getKeyPlayers(){
        return keyPlayers;
    }
    public void setKeyPlayers(Map<String, String> keyPlayers) {
        this.keyPlayers = keyPlayers;
    }
    public String getSport(){
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public int getFlagCount(){
        return flagCount;
    }
    public void setFlagCount(int flagCount){
        this.flagCount = flagCount;
    }
    public String getTopic(){
        return topic;
    }
    public void setTopic(String topic){
        this.topic = topic;
    }
    public String getComments(){
        return comments;
    }
    public void setComments(){
        this.comments = comments;
    }








}
