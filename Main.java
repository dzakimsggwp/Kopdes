import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Membership[] daftarMember = new Membership[100];
        int jumlahMember = 0;
        boolean login = false;
        String id = "";
        
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
                    id = in.nextLine();
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