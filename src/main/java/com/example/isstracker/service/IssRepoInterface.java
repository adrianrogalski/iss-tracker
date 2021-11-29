package com.example.isstracker.service;

public interface IssRepoInterface {
    void getCurrentIssData();
    double getLatitude();
    double getLongitude();
    String getTimestamp();
}
