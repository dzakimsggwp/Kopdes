import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Membership[] daftarMember = new Membership[100];
        int jumlahMember = 0;

        System.out.println("======== KOPERASI DESA MERAH PUTIH ========");
        
        for (;;) {
            System.out.println("1. Daftar Member");
            System.out.println("2. Login Member");
            System.out.println("3. Lihat Menu");
            System.out.println("4. Keluar");
            int choice = in.nextInt();
            in.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (jumlahMember >= daftarMember.length) {
                        System.out.println("Kapasitas Member Sudah Penuh!");
                        break;
                    }
                    Membership membership = new Membership("", "", "", "", "");
                    membership.newMember(in);
                    daftarMember[jumlahMember] = membership;
                    jumlahMember++;

                    System.out.println("Member berhasil didaftarkan!");
                    System.out.println("ID Member: " + membership.getMemberId());
                    break;
                case 2:
                    
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