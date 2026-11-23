import java.util.Scanner;

public class Transaction {
    private String transactionId;
    private Membership member;
    private Cart cart;
    private String status;
    private int totalBelanja;
    private int uangBayar;
    private int kembalian;
    private static int jumlahTransaksi = 0;
    Scanner in = new Scanner(System.in);

    public Transaction(Membership member, Cart cart) {
        jumlahTransaksi++;

        this.transactionId = String.format("TRX%03d", jumlahTransaksi);
        this.member = member;
        this.cart = cart;
        this.status = "Belum dibayar";
    }

    public void checkout() {
        if (cart.hitungTotal() <= 0) {
                System.out.println("Keranjang masih kosong!");
                return;
        }

        totalBelanja = cart.hitungTotal();
        displayRincian();
    }

    public void displayRincian() {
        System.out.println("\n===== RINCIAN TRANSAKSI =====");
        System.out.println("ID Transaksi: " + transactionId);
        System.out.println("Nama Member: " + member.getMembername());
        System.out.println("Total Belanja: Rp" + totalBelanja);
        System.out.println("Diskon Poin: Rp" + klaimPoin());
        totalBelanja -= klaimPoin();
        System.out.println("Total yang harus dibayar: Rp" + totalBelanja);
        System.out.println("Status: " + status);
    }

    public boolean prosesPembayaran(int uangBayar) {
        if (uangBayar < totalBelanja) {
            System.out.println("Uang pembayaran tidak mencukupi!");
            return false;
        }

        this.uangBayar = uangBayar;
        hitungKembalian();
        selesaikanTransaksi();

        return true;
    }

    public void hitungKembalian() {
        kembalian = uangBayar - totalBelanja;
    }

    public void selesaikanTransaksi() {
        int poin = totalBelanja / 1000;

        member.addPoints(totalBelanja);
        status = "Selesai";

        System.out.println("\n===== PEMBAYARAN BERHASIL =====");
        System.out.println("Uang Bayar: Rp" + uangBayar);
        System.out.println("Kembalian: Rp" + kembalian);
        System.out.println("Poin Diperoleh: " + poin);

        cart.kosongkanKeranjang();
    }

    public int klaimPoin() {
        int poin = member.getMemberPoints();
        if (poin <= 0) {
            System.out.println("Anda tidak memiliki poin untuk diklaim.");
            return 0;
        }

        System.out.println("Anda memiliki " + poin + " poin.");
        System.out.print("Apakah Anda ingin mengklaim poin ini? (y/n): ");
        String pilihan = in.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            int diskon = poin * 100; 
            System.out.println("Poin berhasil diklaim! Anda mendapatkan diskon sebesar Rp" + diskon);
            member.addPoints(-poin);
            return diskon;
        } else {
            System.out.println("Klaim poin dibatalkan.");
            return 0;
        }
    }

}
