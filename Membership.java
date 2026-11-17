import java.util.*;

public class Membership {
    Random random =  new Random();

    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberPassword;
    private int memberPoints = 0;

    public Membership(String memberId, String memberName, String memberEmail, String memberPhone, String memberPassword) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberPassword = memberPassword;
    }

    public String getMemberId() {
        return memberId;
    }
    public String getMembername() {
        return memberName;
    }
    public String getMemberEmail() {
        return memberEmail;
    }
    public String getMemberPhone() {
        return memberPhone;
    }
    public int getMemberPoints() {
        return memberPoints;
    }
    public String getMemberPassword() {
    return memberPassword;
    }

    public Membership newMember(Scanner in) {
        System.out.println("\n===== PENDAFTARAN MEMBER =====");
        System.out.println("Masukkan nama anda: ");
        memberName = in.nextLine();
        System.out.println("Masukkan password: ");
        memberPassword = in.nextLine();
        System.out.println("Masukkan nomor telepon: ");
        memberPhone = in.nextLine();
        System.out.println("Masukkan alamat email: ");
        memberEmail = in.nextLine();
        memberId = newId();
        return new Membership(
        memberId,
        memberName,
        memberEmail,
        memberPhone,
        memberPassword);
    }

    public String newId() {
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

    public void displayMember() {
        System.out.println("ID Member: " + memberId);
        System.out.println("Nama Member: " + memberName);
        System.out.println("Email Member: " + memberEmail);
        System.out.println("Nomor Telepon Member: " + memberPhone);
        System.out.println("Password Member: " + memberPassword);
        System.out.println("Poin Member: " + memberPoints);
    }

    public void addPoints(int points) {
        memberPoints += points;
    }

    public void redeemPoints(int points) {
        if (memberPoints >= points) {
            memberPoints -= points;
            System.out.println("Poin berhasil ditukarkan!");
        } else {
            System.out.println("Poin tidak cukup untuk ditukarkan.");
        }
    }

    public void updateMember() {
        System.out.println("Masukkan nama baru: ");
        memberName = in.nextLine();
        System.out.println("Masukkan password baru: ");
        memberPassword = in.nextLine();
        System.out.println("Masukkan nomor telepon baru: ");
        memberPhone = in.nextLine();
        System.out.println("Masukkan alamat email baru: ");
        memberEmail = in.nextLine();
    }

public static int deleteMember(Membership[] daftarMember, int jumlahMember, String id) {
    int index = -1;
    for (int i = 0; i < jumlahMember; i++) {
        if (daftarMember[i].getMemberId().equalsIgnoreCase(id)) {
            index = i;
            break;
        }
    }
    if (index == -1) {
        System.out.println("Member tidak ditemukan!");
        return jumlahMember;
    }
    for (int i = index; i < jumlahMember - 1; i++) {
        daftarMember[i] = daftarMember[i + 1];
    }
    daftarMember[jumlahMember - 1] = null;
    System.out.println("Member berhasil dihapus!");
    return jumlahMember - 1;
    }   

}