package model;

import java.io.Serializable;

public class Computer implements Serializable {
    private int Id;
    private int status = 0;
    private int isReady = 0;
    private String acc = "trống";

    public Computer(int Id,int status,int isReady,String acc) {
        this.Id = Id;
        this.status = status;
        this.isReady = isReady;
        this.acc = acc;
    }

    public Computer() {
    }

    public int getIsReady() {
        return isReady;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String checkIsReady(){
        if(status == 0){
            return "Tắt";
        } else if(status == 1){
            if(isReady == 0){
                return "Sẵn Sàng";
            } else  if(isReady == 1){
                return "Đang sử dụng";
            }
        }
        return null;
    }
    public String checkStatus(){
        if(status == 0){
            return "Tắt";
        } else  if(status == 1){
            return "Bật";
        }
        return null;
    }
    @Override
    public String toString() {
        return "Máy: " + Id +
                ", Trạng thái: " + checkStatus() +
                ", Tình trạng: " + checkIsReady() +
                ", Sử dụng bởi: " + getAcc();
    }
}
