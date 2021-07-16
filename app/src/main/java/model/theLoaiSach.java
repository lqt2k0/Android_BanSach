package model;

public class theLoaiSach {
    public int maTheLoai;
    public String tenTheLoai;
    public String hinhAnhTheLoai;


    public theLoaiSach(int maTheLoai, String tenTheLoai, String hinhAnhTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.hinhAnhTheLoai = hinhAnhTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getHinhAnhTheLoai() {
        return hinhAnhTheLoai;
    }

    public void setHinhAnhTheLoai(String hinhAnhTheLoai) {
        this.hinhAnhTheLoai = hinhAnhTheLoai;
    }



}
