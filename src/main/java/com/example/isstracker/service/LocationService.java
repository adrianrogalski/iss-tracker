package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    double getVelocity();
    List<String> getUpcomingMileages(String longitude, String latitude);
    double getAverageVelocity(LocalDate dateFrom, LocalDate dateTo);
    String getMileageFromPeriod(LocalDate dateFrom, LocalDate dateTo);
}
