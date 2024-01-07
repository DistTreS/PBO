package tugaspbo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Penginapan {
    Scanner scanner = new Scanner(System.in);
    String noKamar;
    int hargaPermalam;
    String status;

    

    public void detailLayanan(){
        ArrayList<String> kamarStandart = new ArrayList<>();
        kamarStandart.add("1 kasur Queen bed");
        kamarStandart.add("AC");
        kamarStandart.add("TV");
        kamarStandart.add("1 Kamar Mandi");


        ArrayList<String> kamarVip = new ArrayList<>();
        kamarVip.add("1 kasur King Bed");
        kamarVip.add("AC");
        kamarVip.add("TV");
        kamarVip.add("Sofa");
        kamarVip.add("2 Kamar Mandi");
        kamarVip.add("Pesan Makanan ke Kamar");

        System.out.println("");
        System.out.println("---------------------------------");
        System.out.println("Selamat Datang di Penginapan kami");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.printf("\nDetai Layanan");
        System.out.println("");
        System.out.println("Layanan Kamar Standart");
        System.out.println("Layanan dan Fasilitas : ");
        for(String kamarStandartItem : kamarStandart){
            System.out.println("- "+kamarStandartItem);
            }
        System.out.println("Harga = Rp. 150.000,00- /malam");
        System.out.println("");
        System.out.println("Layanan Kamar VIP");
        System.out.println("Layanan dan Fasilitas : ");
        for(String kamarVipItem : kamarVip){
        System.out.println("- "+kamarVipItem);
            }
        System.out.println("Harga = Rp. 250.000,00- /malam");
        System.out.println("");
    }

    public void hapusKamar(){
        System.out.print("Masukkan nomor kamar yang akan dihapus: ");
        String nomorKamar = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/penginapan", "root", "");

            // Query untuk mencari data kamar berdasarkan nomor kamar
            String queryCari = "SELECT * FROM kamar WHERE no_kamar = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, nomorKamar);

            // Eksekusi query pencarian
            int jumlahData = 0;
            boolean dataDitemukan = false;

            // Menampilkan data kamar yang akan dihapus
            System.out.println("Data kamar yang akan dihapus:");

            // Eksekusi query pencarian
            try (ResultSet resultSet = preparedStatementCari.executeQuery()) {
                while (resultSet.next()) {
                    dataDitemukan = true;
                    jumlahData++;
                    System.out.println("Nomor Kamar: " + resultSet.getString("no_kamar"));
                    System.out.println("Harga per Malam: " + resultSet.getInt("harga_permalam"));
                    System.out.println("Status: " + resultSet.getString("status"));
                }
            }

            // Jika data ditemukan, minta konfirmasi untuk menghapus
            if (dataDitemukan) {
                System.out.print("Apakah Anda yakin ingin menghapus kamar ini? (y/n): ");
                String konfirmasi = scanner.nextLine().toLowerCase();

                if (konfirmasi.equals("y")) {
                    // Jika pengguna mengonfirmasi, lakukan penghapusan
                    String queryHapus = "DELETE FROM kamar WHERE no_kamar = ?";
                    PreparedStatement preparedStatementHapus = connection.prepareStatement(queryHapus);
                    preparedStatementHapus.setString(1, nomorKamar);

                    // Eksekusi query hapus
                    int hasil = preparedStatementHapus.executeUpdate();

                    if (hasil > 0) {
                        System.out.println("Data kamar berhasil dihapus dari database.");
                    } else {
                        System.out.println("Gagal menghapus data kamar dari database.");
                    }

                    preparedStatementHapus.close();
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }
            } else {
                System.out.println("Data kamar dengan nomor " + nomorKamar + " tidak ditemukan.");
            }

            // Menutup koneksi
            preparedStatementCari.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}