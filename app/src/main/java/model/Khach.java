package model;

public class Khach {
    public String emailKhach;
    public String tenKhach;
    public String sdtKhach;

    public String getEmailKhach() {
        return emailKhach;
    }

    public void setEmailKhach(String emailKhach) {
        this.emailKhach = emailKhach;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getSdtKhach() {
        return sdtKhach;
    }

    public void setSdtKhach(String sdtKhach) {
        this.sdtKhach = sdtKhach;
    }

    public String getGioitinhKhach() {
        return gioitinhKhach;
    }

    public void setGioitinhKhach(String gioitinhKhach) {
        this.gioitinhKhach = gioitinhKhach;
    }

    public String getDiaChiKhach() {
        return diaChiKhach;
    }

    public void setDiaChiKhach(String diaChiKhach) {
        this.diaChiKhach = diaChiKhach;
    }

    public String getHinhKhach() {
        return hinhKhach;
    }

    public void setHinhKhach(String hinhKhach) {
        this.hinhKhach = hinhKhach;
    }

    public String gioitinhKhach;
    public String diaChiKhach;

    public Khach(String emailKhach, String tenKhach, String sdtKhach, String gioitinhKhach, String diaChiKhach, String hinhKhach) {
        this.emailKhach = emailKhach;
        this.tenKhach = tenKhach;
        this.sdtKhach = sdtKhach;
        this.gioitinhKhach = gioitinhKhach;
        this.diaChiKhach = diaChiKhach;
        this.hinhKhach = hinhKhach;
    }

    public String hinhKhach;
}
