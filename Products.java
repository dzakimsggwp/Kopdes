import java.util.Scanner;
public class Products {
    private String kode;
    private String nama;
    private int harga;
    private String kategori;
    private static Products[] keranjang = new Products[100];
    private static int[] jumlahBeli = new int[100];
    private static int jumlahItem = 0;

    public Products(String kode, String nama, int harga, String kategori) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public static final Products[] daftarProduct = {
            new Products("P01", "Beras 5kg", 65000, "makanan"),
            new Products("P02", "Beras 10kg", 120000, "makanan"),
            new Products("P03", "Daging Sapi Wagyu A5 1kg", 3500000, "makanan"),
            new Products("P04", "Daging Sapi 1kg", 140000, "makanan"),
            new Products("P05", "Daging Ayam 1kg", 36000, "makanan"),
            new Products("P06", "Daging Bebek 1kg", 75000, "makanan"),
            new Products("P07", "Cumi Segar", 55000, "makanan"),
            new Products("P08", "Telur Ayam 1kg", 25000, "makanan"),
            new Products("P09", "Telur Bebek 1kg", 65000, "makanan"),
            new Products("P10", "Jamur Tiram 1kg", 25000, "makanan"),
            new Products("P11", "Jamur Enoki 1kg", 45000, "makanan"),
            new Products("P12", "Ikan Lele 1kg", 65000, "makanan"),
            new Products("P13", "Ikan Tuna 1kg", 105000, "makanan"),
            new Products("P14", "Chitato", 15000, "makanan"),
            new Products("P15", "Roti AOKA", 5000, "makanan"),
            new Products("P16", "Biskuit Roma Kelapa", 10000, "makanan"),
            new Products("P17", "Indomie", 3000, "makanan"),
            new Products("P18", "Wafer Tango", 6000, "makanan"),
            new Products("P19", "Pocky collab JKT48", 25000, "makanan"),
            new Products("P20", "Tahu Kocek", 15000, "makanan"),
            new Products("P21", "Teh Botol", 10000, "minuman"),
            new Products("P22", "Aneka Jus Buah", 15000, "minuman"),
            new Products("P23", "Es Jeruk", 7000, "minuman"),
            new Products("P24", "Pocari Sweat", 13000, "minuman"),
            new Products("P25", "Yakult", 5000, "minuman"),
            new Products("P26", "Good Day", 9000, "minuman"),
            new Products("P27", "Floridina", 6000, "minuman"),
            new Products("P28", "Fruit Tea", 5000, "minuman"),
            new Products("P29", "Golda Coffee", 3000, "minuman"),
            new Products("P30", "Ultra Milk", 8000, "minuman"),

        };

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public String getKategori() { return kategori; }

    public void displayProduct() {
        System.out.printf("%-6s %-26s Rp%-10d%n", kode, nama, harga);
    }
    public static Products cariProduk(String kode){
        for (Products p : daftarProduct){
            if (p.getKode().equalsIgnoreCase(kode)){
                return p;
            }
        }
        return null;
    }
        public static void tambahKeKeranjang(Scanner in) {
        System.out.println("\nKetik kode produk untuk dimasukkan ke keranjang (ketik 0 jika selesai)");
        while (true) {
            System.out.print("Kode produk: ");
            String kode = in.nextLine();
            if (kode.equals("0")) {
                break;
            }

            Products p = cariProduk(kode);
            if (p == null) {
                System.out.println("Kode produk tidak ditemukan!\n");
                continue;
            }

            System.out.print("Jumlah beli: ");
            int qty = in.nextInt();
            in.nextLine();
            if (qty <= 0) {
                System.out.println("Jumlah harus lebih dari 0!\n");
                continue;
            }

            boolean sudahAda = false;
            for (int i = 0; i < jumlahItem; i++) {
                if (keranjang[i].getKode().equalsIgnoreCase(p.getKode())) {
                    jumlahBeli[i] += qty;
                    sudahAda = true;
                    break;
                }
            }
            if (!sudahAda) {
                keranjang[jumlahItem] = p;
                jumlahBeli[jumlahItem] = qty;
                jumlahItem++;
            }

            System.out.println(qty + "x " + p.getNama() + " masuk keranjang!\n");
        }
            lihatKeranjang();
    }
        public static int hitungTotal() {
        int total = 0;
        for (int i = 0; i < jumlahItem; i++) {
            total += keranjang[i].getHarga() * jumlahBeli[i];
        }
        return total;
    }

    public static void lihatKeranjang() {
        if (jumlahItem == 0) {
            System.out.println("Keranjang masih kosong.\n");
            return;
        }

        System.out.println("\n========== KERANJANG ==========");
        for (int i = 0; i < jumlahItem; i++) {
            int subtotal = keranjang[i].getHarga() * jumlahBeli[i];
            System.out.printf("%-6s %-26s %3d x Rp%-9d = Rp%d%n",
                    keranjang[i].getKode(),
                    keranjang[i].getNama(),
                    jumlahBeli[i],
                    keranjang[i].getHarga(),
                    subtotal);
        }
        System.out.println("-----------------------------------");
        System.out.println("TOTAL BAYAR: Rp" + hitungTotal() + "\n");
    }
}