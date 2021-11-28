package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    double getVelocity(IssNow iss);
    List<String> getUpcomingMileages(String longitude, String latitude);
    double getAverageVelocity(LocalDate dateFrom, LocalDate dateTo);
    String getMileageFromPeriod(LocalDate dateFrom, LocalDate dateTo);
}
