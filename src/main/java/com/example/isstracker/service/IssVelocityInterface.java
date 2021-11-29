package com.example.isstracker.service;

import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.model.issnow.Velocity;

public interface IssVelocityInterface {
    Velocity getVelocity();
    double getAverageVelocity();
}
