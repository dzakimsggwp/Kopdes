import java.util.*;

public class Membership {
    Scanner in = new Scanner(System.in);
    Random random =  new Random();

    private String memberId = "";
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
    public String getMemberAddress() {
        return memberAddress;
    }
    public String getMemberPoints() {
        return memberPoints;
    }

    public void newMember() {
        System.out.println("Masukkan nama anda: ");
        memberName = in.nextLine();
        System.out.println("Masukkan password: ");
        memberPassword = in.nextLine();
        System.out.println("Masukkan nomor telepon: ");
        memberPhone = in.nextLine();
        System.out.println("Masukkan alamat email: ");
        memberEmail = in.nextLine();
        memberId = newId();
    }

    public String newId() {
        for (int i = 0; i < 3; i++) {
        char huruf = (char) (random.nextInt(26) + 'A');
        memberId += huruf;
        }

        for (int i = 0; i < 3; i++) {
            int angka = random.nextInt(10);
            memberId += angka;
        }
        return memberId;
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

    public void deleteMember() {
        memberId = "";
        memberName = "";
        memberEmail = "";
        memberPhone = "";
        memberPassword = "";
        memberPoints = 0;
    }

}