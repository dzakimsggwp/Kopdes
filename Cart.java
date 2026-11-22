import java.util.Scanner;

public class Cart {
    private static Products[] keranjang = new Products[100];
    private static int[] jumlahBeli = new int[100];
    private static int jumlahItem = 0;

     public static void tambahKeKeranjang(Scanner in) {
        while (true) {
            System.out.println("\nKetik kode produk untuk dimasukkan ke keranjang (ketik 0 jika selesai)");
            System.out.print("Kode produk: ");
            String kode = in.nextLine();
            if (kode.equals("0")) {
                break;
            }

            Products p = Products.cariProduk(kode);
            if (p == null) {
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

            System.out.println(qty + "x " + p.getNama() + " masuk keranjang!");
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
            System.out.println("Keranjang masih kosong.");
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
        System.out.println("TOTAL BAYAR: Rp" + hitungTotal());
        System.out.println("POIN YANG DIDAPAT: " + hitungPoin());
    }

    public static int hitungPoin() {
    return hitungTotal() / 1000;
    }

    public void kosongkanKeranjang() {
    for (int i = 0; i < jumlahItem; i++) {
        keranjang[i] = null;
        jumlahBeli[i] = 0;
    }

    jumlahItem = 0;
}

}

