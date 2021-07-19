package model;

import java.io.Serializable;

public class Sach implements Serializable {
    public int MaSach;
    public String TenSach;
    public String HinhAnh;
    public String MoTa;
    public int MaTheLoai;
    public int MaTacGia;
    public Integer GiaBan;

    public Sach(int maSach, String tenSach, String hinhAnh, String moTa, int maTheLoai, int maTacGia, Integer giaBan, float rating) {
        MaSach = maSach;
        TenSach = tenSach;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        MaTheLoai = maTheLoai;
        MaTacGia = maTacGia;
        GiaBan = giaBan;
        Rating = rating;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public float Rating;

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        MaTheLoai = maTheLoai;
    }

    public int getMaTacGia() {
        return MaTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        MaTacGia = maTacGia;
    }

    public Integer getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Integer giaBan) {
        GiaBan = giaBan;
    }


    public Sach(int maSach, String tenSach, String hinhAnh, String moTa, int maTheLoai, int maTacGia, Integer giaBan) {
        MaSach = maSach;
        TenSach = tenSach;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        MaTheLoai = maTheLoai;
        MaTacGia = maTacGia;
        GiaBan = giaBan;
    }



}
