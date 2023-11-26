import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Member member1=new MemberReguler(10);

        System.out.println("Poin member1 adalah : " + member1.getPoin());
        System.out.println("");

        Scanner scanner = new Scanner(System.in);

        try {
            Member tes1 = new Member(){};
            System.out.print("Masukkan Nama Pelanggan: ");
            tes1.namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan No. Faktur: ");
            tes1.nomorFaktur = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            tes1.namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang: ");
            tes1.hargaBarang = scanner.nextLong();

            System.out.print("Masukkan Jumlah Barang: ");
            tes1.jumlahBarang = scanner.nextLong();

            // Menghitung total bayar
            tes1.totalBayar = tes1.hargaBarang * tes1.jumlahBarang;

            // Menampilkan hasil
            System.out.println("");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("Nama Pelanggan: " + tes1.namaPelanggan);
            System.out.println("No. Faktur: " + tes1.nomorFaktur);
            System.out.println("Nama Barang: " + tes1.namaBarang);
            System.out.println("Harga Barang: " + tes1.hargaBarang);
            System.out.println("Jumlah Barang: " + tes1.jumlahBarang);
            System.out.println("Total Bayar: " + tes1.totalBayar);
            System.out.println("-------------------------");

        } catch (java.util.InputMismatchException e) {
            System.out.println("Maaf, input tidak valid. Pastikan Anda memasukkan nilai numerik untuk harga dan jumlah barang.");
        } finally {
            // Menutup scanner setelah selesai digunakan
            scanner.close();
        }
    }
}