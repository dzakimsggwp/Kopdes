import java.util.*;

public class Procedural {
    static Scanner in = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
    String [] memberId = new String[100];
    String [] memberName = new String[100];
    String [] memberPw = new String[100];
    int jumlahMember = 0;
    boolean login = false;

    String [] productCode = {"P01", "P02", "P03", "P04", "P05", "P06", "P07", "P08", "P09", "P10", "P11", "P12", "P13", "P14", "P15"};
    String [] productName = {"Beras", "Daging Sapi", "Daging Ayam", "Tepung", "Gula", "Minyak", "Telur", "Susu", "Mie Instan", "Kopi", "Teh", "Cokelat", "Biskuit", "Permen", "Roti"};
    int [] productPrice = {50000, 150000, 30000, 20000, 25000, 40000, 25000, 30000, 10000, 20000, 15000, 25000, 20000, 10000, 15000};

    String [] cartProductName = new String[100];
    int [] cartQuantity = new int[100];
    int cartCount = 0;

    for(;;) {
        menu();
        int choice = in.nextInt();
        in.nextLine(); 

        switch (choice) {
            case 1:
                daftarMember(memberId, memberName, memberPw, jumlahMember);
                jumlahMember++;
                break;
            case 2:
                login = loginMember(memberId, memberName, memberPw, jumlahMember, login);
                break;
            case 3:
                lihatMenu(productCode, productName, productPrice);
                cartCount = tambahKeKeranjang(productCode, productName, productPrice, cartProductName, cartQuantity, cartCount);
                break;
            case 4:
                lihatKeranjang(cartProductName, cartQuantity, cartCount);
                break;
            case 5:
                cartCount = transaksi(productName, productPrice, cartProductName, cartQuantity, cartCount);
                break;
            case 6:
                login = false;
                System.out.println("Anda telah logout.");
                break;
            case 7:
                System.out.println("Terima kasih telah menggunakan program ini!");
                return;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}

    public static String newId() {
    Random random = new Random();
    String id = "";
    for (int i = 0; i < 3; i++) {
    char huruf = (char) (random.nextInt(26) + 'A');
    id += huruf;
    }

    for (int i = 0; i < 3; i++) {
    int angka = random.nextInt(10);
    id += angka;
    }
    return id;
    }

    public static void menu() {
            System.out.println("\n======== KOPERASI DESA MERAH PUTIH ========");
            System.out.println("1. Daftar Member");
            System.out.println("2. Login Member");
            System.out.println("3. Lihat Menu");
            System.out.println("4. Lihat Keranjang");
            System.out.println("5. Checkout");
            System.out.println("6. Logout");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");
    }

    public static void daftarMember(String[] memberId, String[] memberName, String[] memberPw, int jumlahMember) {
        System.out.println("\n===== PENDAFTARAN MEMBER =====");
        System.out.print("Masukkan nama: ");
        String nama = in.nextLine();
        System.out.print("Masukkan password: ");
        String password = in.nextLine();
        String id = newId();

        memberId[jumlahMember] = id;
        memberName[jumlahMember] = nama;
        memberPw[jumlahMember] = password;

        System.out.println("Member berhasil didaftarkan!");
        System.out.println("ID Member: " + id);
    }

    public static boolean loginMember(String[] memberId, String[] memberName, String[] memberPw, int jumlahMember, boolean login) {
        if (login) {
            System.out.println("Anda sudah login!");
            return true;
        }
        System.out.print("Masukkan ID Member: ");
        String id = in.nextLine();
        System.out.print("Masukkan Password: ");
        String passwordLogin = in.nextLine();

        for (int i = 0; i < jumlahMember; i++) {
            if (memberId[i].equalsIgnoreCase(id) && memberPw[i].equals(passwordLogin)) {
                System.out.println("Login berhasil!");
                System.out.println("ID Member: " + memberId[i]);
                System.out.println("Nama Member: " + memberName[i]);
                login = true;
                break;
            }
        }
        if (!login) {
            System.out.println("Login gagal! ID atau password salah.");
        }
        return login;
    }
    

    public static void lihatMenu(String[] productCode, String[] productName, int[] productPrice) {
        System.out.println("\n===== DAFTAR PRODUK =====");
        System.out.printf("%-6s %-20s %-10s%n", "Kode", "Nama Produk", "Harga");
        for (int i = 0; i < productCode.length; i++) {
            System.out.printf("%-6s %-20s Rp%-10d%n", productCode[i], productName[i], productPrice[i]);
        }
    }


    public static void lihatKeranjang(String[] cartProductName, int[] cartQuantity, int cartCount) {
        if (cartCount == 0) {
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.println("\n===== KERANJANG BELANJA =====");
        System.out.printf("%-20s %-10s%n", "Nama Produk", "Jumlah");
        for (int i = 0; i < cartCount; i++) {
            System.out.printf("%-20s %-10d%n", cartProductName[i], cartQuantity[i]);
        }
    }

    
    public static int cariProduk(String kode, String[] productCode) {
        for (int i = 0; i < productCode.length; i++) {
            if (productCode[i].equalsIgnoreCase(kode)) {
                return i;
            }
     }

        return -1;
    }

    public static int tambahKeKeranjang(String[] productCode, String[] productName, int[] productPrice, String[] cartProductName, int[] cartQuantity, int cartCount) {
        while (true) {
            System.out.println("\nKetik kode produk untuk dimasukkan ke keranjang (0 untuk selesai)");
            System.out.print("Kode produk: ");
            String kode = in.nextLine();

            if (kode.equals("0")) {
                break;
            }

            int p = cariProduk(kode, productCode);

            if (p == -1) {
                System.out.println("Kode produk tidak ditemukan!");
                continue;
            }

            System.out.print("Jumlah beli: ");
            int qty = in.nextInt();
            in.nextLine();

            if (qty <= 0) {
                System.out.println("Jumlah harus lebih dari 0!");
                continue;
            }

            boolean sudahAda = false;

            for (int i = 0; i < cartCount; i++) {
                if (cartProductName[i].equalsIgnoreCase(productName[p])) {
                    cartQuantity[i] += qty;
                    sudahAda = true;
                    break;
                }
            }

            if (!sudahAda) {
                if (cartCount >= cartProductName.length) {
                    System.out.println("Keranjang sudah penuh!");
                    break;
                }

                cartProductName[cartCount] = productName[p];
                cartQuantity[cartCount] = qty;
                cartCount++;
            }

            System.out.println(qty + "x " + productName[p] + " berhasil dimasukkan ke keranjang!");
        }

        lihatKeranjang(cartProductName, cartQuantity, cartCount);

        return cartCount;
    }


    public static int transaksi(String[] productName, int[] productPrice, String[] cartProductName, int[] cartQuantity, int cartCount) {
        if (cartCount == 0) {
            System.out.println("\nKeranjang masih kosong!");
            return cartCount;
        }

        int total = 0;

        System.out.println("\n========== STRUK TRANSAKSI ==========");
        System.out.printf("%-20s %-8s %-12s %-12s%n",
                "Produk", "Jumlah", "Harga", "Subtotal");

        for (int i = 0; i < cartCount; i++) {
            int harga = 0;

            for (int j = 0; j < productName.length; j++) {
                if (cartProductName[i].equalsIgnoreCase(productName[j])) {
                    harga = productPrice[j];
                    break;
                }
            }

            int subtotal = harga * cartQuantity[i];
            total += subtotal;

            System.out.printf("%-20s %-8d Rp%-10d Rp%-10d%n",
                    cartProductName[i],
                    cartQuantity[i],
                    harga,
                    subtotal);
        }

        System.out.println("-------------------------------------");
        System.out.println("Total Pembayaran: Rp" + total);

        int bayar;

        while (true) {
            System.out.print("Masukkan nominal uang: Rp");
            bayar = in.nextInt();
            in.nextLine();

            if (bayar < total) {
                System.out.println("Uang tidak mencukupi!");
                System.out.println("Kekurangan: Rp" + (total - bayar));
            } else {
                break;
            }
        }

        int kembalian = bayar - total;

        System.out.println("-------------------------------------");
        System.out.println("Uang Dibayarkan : Rp" + bayar);
        System.out.println("Total Belanja   : Rp" + total);
        System.out.println("Kembalian       : Rp" + kembalian);
        System.out.println("=====================================");
        System.out.println("Transaksi berhasil!");

        for (int i = 0; i < cartCount; i++) {
            cartProductName[i] = null;
            cartQuantity[i] = 0;
        }

        return 0;
    }

}

