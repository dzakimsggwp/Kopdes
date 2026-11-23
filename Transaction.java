import java.util.Scanner;

public class Transaction {
    private String transactionId;
    private Membership member;
    private Cart cart;
    private String status;
    private int totalBelanja;
    private int uangBayar;
    private int kembalian;
    private int diskonPoin;
    private int totalAkhir;
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
        totalAkhir = totalBelanja;
        displayRincian();
    }

    public void displayRincian(){
        System.out.println("\n===== RINCIAN TRANSAKSI =====");
        System.out.println("ID Transaksi: " + transactionId);
        System.out.println("Nama Member: " + member.getMembername());
        System.out.println("Total Belanja: Rp" + totalBelanja);
        System.out.println("Diskon Poin: Rp" + diskonPoin);
        System.out.println("Total yang harus dibayar: Rp" + totalAkhir);
        System.out.println("Status: " + status);
    }

    public boolean prosesPembayaran(int uangBayar) {
        if (uangBayar < totalAkhir) {
            System.out.println("Uang pembayaran tidak mencukupi!");
            return false;
        }

        this.uangBayar = uangBayar;
        hitungKembalian();
        selesaikanTransaksi();

        return true;
    }

    public void hitungKembalian() {
        kembalian = uangBayar - totalAkhir;
    }

    public void selesaikanTransaksi() {
        int poin = totalAkhir / 1000;
        member.addPoints(poin);
        status = "Selesai";

        System.out.println("\n===== PEMBAYARAN BERHASIL =====");
        System.out.println("Uang Bayar: Rp" + uangBayar);
        System.out.println("Kembalian: Rp" + kembalian);
        System.out.println("Poin Diperoleh: " + member.getMemberPoints());

        cart.kosongkanKeranjang();
    }

    public void klaimPoin() {
        if (member.getMemberPoints() <= 0) {
            System.out.println("Anda tidak memiliki poin untuk diklaim.");
            return;
        }
        int poin = totalAkhir / 1000;

        System.out.println("Anda memiliki " + poin + " poin.");
        System.out.print("Apakah Anda ingin mengklaim poin ini? (y/n): ");
        String pilihan = in.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            diskonPoin = poin * 100; 
            totalAkhir = totalBelanja - diskonPoin;
            System.out.println("Poin berhasil diklaim! Anda mendapatkan diskon sebesar Rp" + diskonPoin);
            member.addPoints(-poin);
        } else {
            System.out.println("Klaim poin dibatalkan.");
            return;
        }
    }

}
