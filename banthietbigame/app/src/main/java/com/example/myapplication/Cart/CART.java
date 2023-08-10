package com.example.myapplication.Cart;

public class CART {
    public String masp;
    public String maloai;
    public String tensp;
    public String chitietsp;
    public String danhgiasp;
    public Double donggiasp;
    public String hinhsp;
    public int soluongsp;
    public double tongtien;

    public CART() {
    }

    public CART(String masp, String maloai, String tensp, String chitietsp, String danhgiasp, Double donggiasp, String hinhsp) {
        this.masp = masp;
        this.maloai = maloai;
        this.tensp = tensp;
        this.chitietsp = chitietsp;
        this.danhgiasp = danhgiasp;
        this.donggiasp = donggiasp;
        this.hinhsp = hinhsp;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getChitietsp() {
        return chitietsp;
    }

    public void setChitietsp(String chitietsp) {
        this.chitietsp = chitietsp;
    }

    public String getDanhgiasp() {
        return danhgiasp;
    }

    public void setDanhgiasp(String danhgiasp) {
        this.danhgiasp = danhgiasp;
    }

    public Double getDonggiasp() {
        return donggiasp;
    }

    public void setDonggiasp(Double donggiasp) {
        this.donggiasp = donggiasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public CART(String masp, String tensp, Double donggiasp, String hinhsp) {
        this.masp = masp;
        this.tensp = tensp;
        this.donggiasp = donggiasp;
        this.hinhsp = hinhsp;
    }
}
