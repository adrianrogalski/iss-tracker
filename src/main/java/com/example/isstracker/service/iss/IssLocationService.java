package com.example.isstracker.service.iss;

public interface IssLocationService {
    void getCurrentIssData();
    double getLatitude();
    double getLongitude();
    String getTimestamp();
}
