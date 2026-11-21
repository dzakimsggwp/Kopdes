import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Membership[] daftarMember = new Membership[100];
        int jumlahMember = 0;
        boolean login = false;
        
        for (;;) {
            System.out.println("======== KOPERASI DESA MERAH PUTIH ========");
            System.out.println("1. Daftar Member");
            System.out.println("2. Login Member");
            System.out.println("3. Lihat Menu");
            System.out.println("4. Lihat Keranjang");
            System.out.println("5. Keluar");
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
                for (Products daftarProduct1 : Products.daftarProduct) {
                    if (pilihKategori == 3 || daftarProduct1.getKategori().equalsIgnoreCase(filter)) {
                        daftarProduct1.displayProduct();
                    }
                }
                    Products.tambahKeKeranjang(in);
                    
                    break;

                case 4:
                    Products.lihatKeranjang();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
}
}