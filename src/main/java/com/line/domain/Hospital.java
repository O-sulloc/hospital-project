package com.line.domain;

public class Hospital {
    private String id;
    private String address;
    private String district;
    private String category;
    private Integer emergencyRoom;
    private String name;
    private String subdivision;

    private String replaceAll(String str){
        return str.replaceAll("\"","");
    }

    public Hospital(String id, String address) {
        this.id = replaceAll(id);
        this.address = replaceAll(address);
    }

    public Hospital(String id, String address, String category, Integer emergencyRoom, String name, String subdivision) {
        this.id = id;
        this.address = address;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.subdivision = subdivision;
        this.setDistrict();
    }

    public void setDistrict(){
        // address를 잘라서 district에 넣음
        String[] splitted=this.address.split(" ");
        this.district = splitted[0] + " " + splitted[1]; //서울특별시 금천구
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public Integer getEmergencyRoom() {
        return emergencyRoom;
    }

    public String getName() {
        return name;
    }

    public String getSubdivision() {
        return subdivision;
    }
}
