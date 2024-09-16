package com.mycompany.hotelappnon;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class dashboarddatamodel {
    private final StringProperty roomNumber;
    private final StringProperty guestName;

    public dashboarddatamodel(String roomNumber, String guestName) {
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.guestName = new SimpleStringProperty(guestName);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName.get();
    }

    public void setGuestName(String guestName) {
        this.guestName.set(guestName);
    }

    public StringProperty guestNameProperty() {
        return guestName;
    }
}