package com.avengers.avengerstaxi.server.service;

import com.avengers.avengerstaxi.server.model.SchoolEntity;
import com.avengers.avengerstaxi.server.model.TaxiEntity;
import com.avengers.avengerstaxi.server.repository.SchoolRepository;
import com.avengers.avengerstaxi.server.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaxiService {
    private TaxiRepository taxiRepository;
    private static int KM_RADIUS = 6371;
    private static double MAX_SPEED_PER_MIN =1;
    private static int DEFUALT_COST = 3800;
    private static int EXTRA_COST = 100;
    private static int DEFUALT_COST_DISTANCE= 2;


    @Autowired //스쿨 레파지토리를 자동으로 넣어줘 (넘겨받을 수 있는 건 자동으로 넘겨주세요! @가 적혀있는 것들만 받을 수 있음)
    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public ArrayList<TaxiEntity> findTaxiList(){
        ArrayList<TaxiEntity> taxiList = new ArrayList<>(taxiRepository.findAll());
        // findAll() (데이터 베이스에 있는 내용 모두 불러오기)
        return taxiList;
    }

    private double degreeToRadian(double degree) {
        return degree * Math.PI / 180;
    }

    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distanceLatitude = degreeToRadian(latitude1 -latitude2);
        double distanceLongitude = degreeToRadian(longitude1-longitude2);
        double value = Math.pow(Math.sin(distanceLatitude / 2), 2)
                + Math.pow(Math.sin(distanceLongitude / 2), 2)
                * Math.cos(degreeToRadian(latitude1)) * Math.cos(degreeToRadian(latitude2));
        double c = 2 * Math.atan2(Math.sqrt(value), Math.sqrt(1 - value));
        return KM_RADIUS * c;
    }

    public void addTaxiPosition(TaxiEntity taxi) {
        this.taxiRepository.save(taxi);
    }

    public int calculateEstimateTime(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distance = calculateDistance(latitude1, longitude1, latitude2, longitude2);
        double movePerMinute = distance / this.MAX_SPEED_PER_MIN;
        return (int) Math.ceil(movePerMinute);
    }

    public int calculateEstimateCost(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distance = this.calculateDistance(latitude1, longitude1, latitude2, longitude2);
        if(distance < DEFUALT_COST_DISTANCE) {
            return DEFUALT_COST;
        }
        int extraDistance = (int) Math.ceil((distance - DEFUALT_COST_DISTANCE) / 0.1);
        return DEFUALT_COST + extraDistance * EXTRA_COST;
    }
}
