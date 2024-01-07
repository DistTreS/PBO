package tugaspbo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vip extends Penginapan implements IPesanMakanan{
    Scanner scanner = new Scanner(System.in);
    int hargaPermalam=250000;
    String status ="Tersedia";
    Vip(String noKamar, String status){
        this.noKamar=noKamar;
        this.status=status;
    }

    public Vip() {
    }

    public void TambahkmarVip(){
        System.out.print("Masukkan nomor kamar VIP baru (Dahulukan dengan kode 'V'): ");
        String nomorKamar = scanner.nextLine();
        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/penginapan", "root", "");

            // Query INSERT untuk menambahkan data kamar Vip
            String queryTambah = "INSERT INTO kamar (no_kamar, harga_permalam, status) VALUES (?, ?, ?)";
            PreparedStatement preparedStatementTambah = connection.prepareStatement(queryTambah);
            preparedStatementTambah.setString(1, nomorKamar);
            preparedStatementTambah.setInt(2, this.hargaPermalam);
            preparedStatementTambah.setString(3, this.status);

            // Eksekusi query insert
            int hasil = preparedStatementTambah.executeUpdate();

            if (hasil > 0) {
                System.out.println("Data kamar VIP berhasil ditambahkan ke database.");
            } else {
                System.out.println("Gagal menambahkan data kamar VIP ke database.");
            }

            // Menutup koneksi
            preparedStatementTambah.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pesanMakanan() {

        ArrayList<String> daftarMakanan = new ArrayList<>();
        daftarMakanan.add("Nasi Goreng");
        daftarMakanan.add("Mie Goreng");
        daftarMakanan.add("Mie kuah");
        daftarMakanan.add("Nasi Padang");

        System.out.println("Masukkan nomor kamar : ");
        Scanner scanner = new Scanner(System.in);
        String nomorkamar = scanner.nextLine();

        System.out.println("Berikut List Makanan ");
        for (String Menu : daftarMakanan) {
            System.out.println(Menu);
        }

        System.out.println("Masukkan Makanan Yang Ingin dipesan : ");
        String Menu =scanner.nextLine();

        System.out.println("Makanan akan diantar ke kamar : "+nomorkamar +"  dengan menu : "+Menu );
        System.out.println("Harap ditunggu, Terima Kasih");
    }
}
