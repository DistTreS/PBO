package tugaspbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Standart extends Penginapan{
    Scanner scanner = new Scanner(System.in);
    int hargaPermalam=150000;
    String status ="Tersedia";

    public Standart(String noKamar, String status){
        this.noKamar=noKamar;
        this.status=status;
    }

    public Standart() {
    }

    public void TambahkmarStandart(){
        System.out.print("Masukkan nomor kamar standar baru (Dahulukan dengan kode 'S'): ");
        String nomorKamar = scanner.nextLine();
        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/penginapan", "root", "");

            // Query INSERT untuk menambahkan data kamar standar
            String queryTambah = "INSERT INTO kamar (no_kamar, harga_permalam, status) VALUES (?, ?, ?)";
            PreparedStatement preparedStatementTambah = connection.prepareStatement(queryTambah);
            preparedStatementTambah.setString(1, nomorKamar);
            preparedStatementTambah.setInt(2, this.hargaPermalam);
            preparedStatementTambah.setString(3, this.status);

            // Eksekusi query insert
            int hasil = preparedStatementTambah.executeUpdate();

            if (hasil > 0) {
                System.out.println("Data kamar standar berhasil ditambahkan ke database.");
            } else {
                System.out.println("Gagal menambahkan data kamar standar ke database.");
            }

            // Menutup koneksi
            preparedStatementTambah.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
