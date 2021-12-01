package com.example.isstracker.service;

public interface IssLocationService {
    void getCurrentIssData();
    double getLatitude();
    double getLongitude();
    String getTimestamp();
}
