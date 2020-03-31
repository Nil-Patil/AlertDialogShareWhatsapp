package com.example.customsharemenu;

public class CountryModel {

    private String iso, phoneCode, countryName, countryFlag;

    CountryModel(String iso, String countryName, String phoneCode, String countryFlag) {
        this.iso = iso;
        this.countryName = countryName;
        this.phoneCode = phoneCode;
        this.countryFlag = countryFlag;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }
}
