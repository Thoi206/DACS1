package model;

public class SuKien {
    private String ten;
    private String diaDiem;
    private String ngayBatDau;
    private String gioBatDau;
    private String ngayKetThuc;
    private String gioKetThuc;

    public SuKien(String ten, String diaDiem, String ngayBatDau, String gioBatDau, String ngayKetThuc, String gioKetThuc) {
        this.ten = ten;
        this.diaDiem = diaDiem;
        this.ngayBatDau = ngayBatDau;
        this.gioBatDau = gioBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.gioKetThuc = gioKetThuc;
    }

    public String toString() {
        return ten + " (" + ngayBatDau + " " + gioBatDau + " - " + ngayKetThuc + " " + gioKetThuc + ")";
    }
}
