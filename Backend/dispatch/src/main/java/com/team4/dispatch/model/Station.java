package com.team4.dispatch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "station")
@JsonDeserialize(builder = Station.Builder.class)
public class Station implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JsonProperty("station_ID")
    private int stationID;

    @JsonProperty("station_address")
    private String stationAddress;

    public Station() {}

    public Station(Builder builder) {
        this.stationID = builder.stationID;
        this.stationAddress = builder.stationAddress;
    }

    public int getStationID() {
        return stationID;
    }

    public String stationAddress() {
        return stationAddress;
    }


    public static class Builder {
        @JsonProperty("station_ID")
        private int stationID;

        @JsonProperty("station_address")
        private String stationAddress;

        public Builder setStationID(int stationID) {
            this.stationID = stationID;
            return this;
        }

        public Builder stationAddress(String stationAddress) {
            this.stationAddress = stationAddress;
            return this;
        }

        public Station build() {
            return new Station(this);
        }

    }
}
