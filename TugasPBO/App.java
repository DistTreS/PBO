import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //Member member1=new MemberReguler(10);
        //penerapan pholymorphism dengan inisiasi MemberReguler sebagai Member
        //System.out.println("");
        //System.out.println("Poin member1 adalah : " + member1.getPoin());
        //System.out.println("");


        Scanner scanner = new Scanner(System.in);
        Date tanggal = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String tanggalTransaksi = dateFormat.format(tanggal);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");
        String waktuTransaksi = timeFormat.format(tanggal);
        

        try {
            Member tes1 = new Member(){};
            System.out.print("Masukkan Nama Pelanggan\t: ");
            tes1.namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan Nomor HP\t: ");
            tes1.noHp = scanner.nextLine();

            System.out.print("Masukkan Alamat\t\t: ");
            tes1.alamat = scanner.nextLine();

            System.out.print("Masukkan No. Faktur\t: ");
            tes1.nomorFaktur = scanner.nextLine();

            System.out.print("Masukkan Nama Barang\t: ");
            tes1.namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang\t: ");
            tes1.hargaBarang = scanner.nextLong();

            System.out.print("Masukkan Jumlah Barang\t: ");
            tes1.jumlahBeli = scanner.nextLong();

            // Menghitung total bayar
            tes1.totalBayar = tes1.hargaBarang * tes1.jumlahBeli;

            // Menampilkan hasil
            System.out.println("");
            System.out.println("\t\033[1m\033[31mTres Mart\033[0m");
            System.out.println("Tanggal \t:" + tanggalTransaksi);
            System.out.println("Waktu \t\t:" + waktuTransaksi);
            System.out.println("========================");
            System.out.println("DATA PELANGGAN");
            System.out.println("------------------------");
            System.out.println("Nama Pelanggan\t:" + tes1.namaPelanggan);
            System.out.println("Nomor Hp\t:"+tes1.noHp);
            System.out.println("Alamat\t\t:"+tes1.alamat);
            System.out.println("++++++++++++++++++++++++");
            System.out.println("DATA PEMBELIAN BARANG");
            System.out.println("------------------------");
            System.out.println("No. Faktur\t:" + tes1.nomorFaktur);
            System.out.println("Nama Barang\t:" + tes1.namaBarang);
            System.out.println("Harga Barang\t:" + tes1.hargaBarang);
            System.out.println("Jumlah Beli\t:" + tes1.jumlahBeli);
            System.out.println("TOTAL BAYAR\t:" + tes1.totalBayar);
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Kasir\t\t:" + tes1.namaKasir);
            System.out.println("");


        } catch (java.util.InputMismatchException e) {
            System.out.println("Maaf, input tidak valid. Pastikan Anda memasukkan nilai numerik untuk harga dan jumlah barang.");
        } finally {
            // Menutup scanner setelah selesai digunakan
            scanner.close();
        }
    }
}