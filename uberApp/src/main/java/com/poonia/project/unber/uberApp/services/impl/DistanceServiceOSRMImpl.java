package com.poonia.project.unber.uberApp.services.impl;

import com.poonia.project.unber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static  final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
       try {
           String uri= src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();

           OSRMResponseDto responseDto  = RestClient.builder()
                   .baseUrl(OSRM_API_BASE_URL)
                   .build()
                   .get()
                   //.uri("{},{};{},{}", src.getX(),src.getY(),dest.getX(),dest.getY())// Check here for any error
                   .uri(uri)
                   .retrieve()
                   .body(OSRMResponseDto.class);

           // Distance converted from meters to Km.
           return responseDto.getRoutes().get(0).getDistance()/1000.0;
       } catch (Exception e){
           throw new RuntimeException("Error getting data from OSRM "+e.getMessage());
       }
    }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;

}

@Data
class OSRMRoute {
    private Double distance;
}
