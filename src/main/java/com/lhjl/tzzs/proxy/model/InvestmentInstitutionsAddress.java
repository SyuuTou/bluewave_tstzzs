package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "investment_institutions_address")
public class InvestmentInstitutionsAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所在城市
     */
    private String town;

    /**
     * 省份id
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 区id
     */
    @Column(name = "district_id")
    private Integer districtId;

    /**
     * 详细地址
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 电话国家码
     */
    @Column(name = "phone_country_code")
    private String phoneCountryCode;

    /**
     * 电话区号
     */
    @Column(name = "phone_district_code")
    private String phoneDistrictCode;

    /**
     * 电话号（不包含，国家码，区号）
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 经度坐标
     */
    private BigDecimal longitude;

    /**
     * 纬度坐标
     */
    private BigDecimal latitude;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所在城市
     *
     * @return town - 所在城市
     */
    public String getTown() {
        return town;
    }

    /**
     * 设置所在城市
     *
     * @param town 所在城市
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * 获取省份id
     *
     * @return province_id - 省份id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省份id
     *
     * @param provinceId 省份id
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取城市id
     *
     * @return city_id - 城市id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市id
     *
     * @param cityId 城市id
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取区id
     *
     * @return district_id - 区id
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * 设置区id
     *
     * @param districtId 区id
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取详细地址
     *
     * @return detail_address - 详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 设置详细地址
     *
     * @param detailAddress 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 获取电话国家码
     *
     * @return phone_country_code - 电话国家码
     */
    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    /**
     * 设置电话国家码
     *
     * @param phoneCountryCode 电话国家码
     */
    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    /**
     * 获取电话区号
     *
     * @return phone_district_code - 电话区号
     */
    public String getPhoneDistrictCode() {
        return phoneDistrictCode;
    }

    /**
     * 设置电话区号
     *
     * @param phoneDistrictCode 电话区号
     */
    public void setPhoneDistrictCode(String phoneDistrictCode) {
        this.phoneDistrictCode = phoneDistrictCode;
    }

    /**
     * 获取电话号（不包含，国家码，区号）
     *
     * @return phone_number - 电话号（不包含，国家码，区号）
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号（不包含，国家码，区号）
     *
     * @param phoneNumber 电话号（不包含，国家码，区号）
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取机构id
     *
     * @return investment_institution_id - 机构id
     */
    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionId 机构id
     */
    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
    }

    /**
     * 获取经度坐标
     *
     * @return longitude - 经度坐标
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 设置经度坐标
     *
     * @param longitude 经度坐标
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度坐标
     *
     * @return latitude - 纬度坐标
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度坐标
     *
     * @param latitude 纬度坐标
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}