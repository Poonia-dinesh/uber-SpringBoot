package com.poonia.project.unber.uberApp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

      double calculateDistance(Point src, Point dest);
}
