package com.team4.dispatch.service;

import com.team4.dispatch.model.Station;
import com.team4.dispatch.repository.StationRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class SearchService {

    public String preURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?location=37.7410427%2C-122.4518497&query=";
    public String key = "&radius=30000&key=AIzaSyCrH7_EAaL6C1adwWxMYav_rcDJrZ5X7kI";

    public StationRepository stationRepository;
    public final double speed = 300;
    public final double unitPrice = 0.005;

    @Autowired
    public SearchService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Double> getLngAndLat(String address) throws IOException, JSONException {
        List<Double> res = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String addressURL = preURL + address + key;
        Request request = new Request.Builder()
                .url(addressURL)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        JSONObject jsonobject = new JSONObject(body);
        JSONObject location = jsonobject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        res.add(location.getDouble("lat"));
        res.add(location.getDouble("lng"));
        return res;
    }
    public List<Double> getPriceAndTime(List<Double> pickUp, List<Double> delivery) throws JSONException, IOException {
        List<Double> res = Arrays.asList(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE);  // 0:stationID, 1: time, 2: price
        List<Station> stations = stationRepository.findAll(); // get 3 stations

//        Station.Builder builder = new Station.Builder();
//        Station s1 = new Station(builder.setStationID(1).stationAddress("The Painted Ladies"));
//        Station s2 = new Station(builder.setStationID(2).stationAddress("McCoppin Square"));
//        Station s3 = new Station(builder.setStationID(3).stationAddress("Bernal Heights Park"));
//        List<Station> stations = Arrays.asList(s1,s2,s3);

        double deliveryDist = calculateDistanceInMeters(delivery.get(0), delivery.get(1), pickUp.get(0), pickUp.get(1));

        // 0:stationID, 1:pickUpDist, 2:deliveryDist
        res.set(2,deliveryDist);
        // get the closest station and distance
        for (Station s: stations) {
            List<Double> stationLocation = getLngAndLat(s.stationAddress());
            double pickUpDist = calculateDistanceInMeters(pickUp.get(0), pickUp.get(1), stationLocation.get(0), stationLocation.get(1));
            if (pickUpDist < res.get(1)) {
                res.set(0,(double)s.getStationID());
                res.set(1,pickUpDist);
            }
        }
        // calculate price and price
        double totalDist = res.get(1)+res.get(2);
        res.set(1,totalDist/speed);
        res.set(2,totalDist*unitPrice);
        return res;
    }
    public double calculateDistanceInMeters(double lat1, double long1, double lat2,
                                            double long2) {
        double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        return dist;
    }
}
