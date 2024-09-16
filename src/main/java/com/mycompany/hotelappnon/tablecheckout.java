package com.mycompany.hotelappnon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class tablecheckout {
    private final SimpleLongProperty NIK;
    private final SimpleStringProperty guestName;
    private final SimpleIntegerProperty roomNo;
    private final SimpleLongProperty hpNo;

    public tablecheckout(Long NIK, String guestName, int roomNo, Long hpNo) {
        this.NIK = new SimpleLongProperty(NIK);
        this.guestName = new SimpleStringProperty(guestName);
        this.roomNo = new SimpleIntegerProperty(roomNo);
        this.hpNo = new SimpleLongProperty(hpNo);
    }

    public Long getNIK() {
        return NIK.get();
    }

    public SimpleLongProperty NIKProperty() {
        return NIK;
    }

    public String getGuestName() {
        return guestName.get();
    }

    public SimpleStringProperty guestNameProperty() {
        return guestName;
    }

    public int getRoomNo() {
        return roomNo.get();
    }

    public SimpleIntegerProperty roomNoProperty() {
        return roomNo;
    }

    public Long getHpNo() {
        return hpNo.get();
    }

    public SimpleLongProperty hpNoProperty() {
        return hpNo;
    }
}
