package org.publicwifi.v1.others;

public class Distance {

    // km 기준
    public Double getDistance(Double lat, Double lnt, Double lat2, Double lnt2) {
        double theta = lnt - lnt2;
        double dist = Math.sin(deg2rad(lat))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return Math.round(dist * 10) / 10000.0;  // km 기준 & 소수점 4자리까지
    }

    //10진수를 radian(라디안)으로 변환
    private double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
