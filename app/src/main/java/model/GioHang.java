package model;

public class GioHang {
    public int idSach;
    public String tenSach;
    public long giaSach;
    public String hinhSach;

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public long getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(long giaSach) {
        this.giaSach = giaSach;
    }

    public String getHinhSach() {
        return hinhSach;
    }

    public void setHinhSach(String hinhSach) {
        this.hinhSach = hinhSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int soLuong;

    public GioHang(int idSach, String tenSach, long giaSach, String hinhSach, int soLuong) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.hinhSach = hinhSach;
        this.soLuong = soLuong;
    }
}
