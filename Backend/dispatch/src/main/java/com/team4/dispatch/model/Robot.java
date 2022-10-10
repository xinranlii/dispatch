package com.team4.dispatch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "robot")
@JsonDeserialize(builder = Robot.Builder.class)
public class Robot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int robotID;

    @ManyToOne
    @JoinColumn(name = "station_ID")
    private Station stationID;


    public Robot() {}

    public Robot(Builder builder) {
        this.robotID = builder.robotID;
        this.stationID = builder.stationID;
    }

    public int getRobotID() {
        return robotID;
    }

    public Station getStationID() {
        return stationID;
    }

    public void setStationID(Station stationID) {
        this.stationID = stationID;
    }

    public static class Builder {

        @JsonProperty("robot_ID")
        private int robotID;

        @JsonProperty("station_ID")
        private Station stationID;

        public Builder setRobotID(int robotID) {
            this.robotID = robotID;
            return this;
        }

        public Builder setStationID(Station stationID) {
            this.stationID = stationID;
            return this;
        }

        public Robot build() {
            return new Robot(this);
        }


    }
}
