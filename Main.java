import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Membership[] daftarMember = new Membership[100];
        int jumlahMember = 0;
        boolean login = false;
        Products[] daftarProduct = {
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
        
        for (;;) {
            System.out.println("======== KOPERASI DESA MERAH PUTIH ========");
            System.out.println("1. Daftar Member");
            System.out.println("2. Login Member");
            System.out.println("3. Lihat Menu");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int choice = in.nextInt();
            in.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n===== PENDAFTARAN MEMBER =====");
                    System.out.print("Masukkan nama: ");
                    String nama = in.nextLine();
                    System.out.print("Masukkan email: ");
                    String email = in.nextLine();
                    System.out.print("Masukkan nomor HP: ");
                    String phone = in.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = in.nextLine();
                    Membership memberBaru = new Membership("", nama, email, phone, password);
                    memberBaru.newId();

                    daftarMember[jumlahMember] = memberBaru;
                    jumlahMember++;

                    System.out.println("Member berhasil didaftarkan!");
                    System.out.println("ID Member: " + memberBaru.getMemberId());
                    break;
                case 2:
                    if (login) {
                        System.out.println("Anda sudah login!\n");
                        break;
                    }
                    System.out.print("Masukkan ID Member: ");
                    String id = in.nextLine();
                    System.out.print("Masukkan Password: ");
                    String passwordLogin = in.nextLine();

                    for (int i = 0; i < jumlahMember; i++) {
                        if (daftarMember[i].getMemberId().equalsIgnoreCase(id) && daftarMember[i].getMemberPassword().equals(passwordLogin)) {
                            login = true;
                            System.out.println("Login berhasil!\n");
                            daftarMember[i].displayMember();
                            break;
                        }
                    }
                    if (!login) {
                        System.out.println("ID Member atau Password salah!\n");
                    }
                    break;
                case 3:
                    System.out.println("\n===== LIHAT PRODUK =====");
                    System.out.println("1. Makanan");
                    System.out.println("2. Minuman");
                    System.out.println("3. Semua");
                    System.out.print("Pilih kategori: ");
                    int pilihKategori = in.nextInt();
                    in.nextLine();

                    if (pilihKategori < 1 || pilihKategori > 3) {
                        System.out.println("Pilihan tidak valid!\n");
                        break;
                    }

                    String filter = "";
                    if (pilihKategori == 1) {
                        filter = "Makanan";
                    } else if (pilihKategori == 2) {
                        filter = "Minuman";
                    }

                    System.out.println("\n========== Daftar Produk ==========");
                for (Products daftarProduct1 : daftarProduct) {
                    if (pilihKategori == 3 || daftarProduct1.getKategori().equalsIgnoreCase(filter)) {
                        daftarProduct1.displayProduct();
                    }
                }
                    
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
}
}