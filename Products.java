public class Products {
    private String kode;
    private String nama;
    private int harga;
    private String kategori;

    public Products(String kode, String nama, int harga, String kategori) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public String getKategori() { return kategori; }

    public void displayProduct() {
        System.out.printf("%-6s %-26s Rp%-10d%n", kode, nama, harga);
    }
}