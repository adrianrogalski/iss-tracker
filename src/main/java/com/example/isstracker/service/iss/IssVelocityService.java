package com.example.isstracker.service.iss;

import com.example.isstracker.model.issnow.IssNow;
import com.example.isstracker.model.issnow.Velocity;

public interface IssVelocityService {
    Velocity getVelocity(int millis);
}
