package com.example.SandBox.data;

import jakarta.persistence.*;

@Entity
@Table(name="Reservation")
public class Reservation {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resId;

    @Column(name = "ROOM_ID")
    private String roomId;
    @Column(name = "GUEST_ID")
    private String guestId;
    @Column(name = "RES_DATE")
    private String resDate;

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }
}
