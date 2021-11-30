package com.example.isstracker.service;

public interface IssLocationServiceInterface {
    void getCurrentIssData();
    double getLatitude();
    double getLongitude();
    String getTimestamp();
}
