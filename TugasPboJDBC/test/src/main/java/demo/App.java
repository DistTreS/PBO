package demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App 
{
    private static boolean login(String correctUsername, String correctPassword, String inputUsername, String inputPassword) {
        return correctUsername.equals(inputUsername) && correctPassword.equals(inputPassword);
    }

    private static String generateCaptcha() {
        return "Si49D6j";
    }

    private static boolean checkCaptcha(String correctCaptcha, String inputCaptcha) {
        return correctCaptcha.equalsIgnoreCase(inputCaptcha);
    }

    private static void tambahData(Connection connection) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan Nomor HP: ");
            String nomorHp = scanner.nextLine();

            System.out.print("Masukkan Total Transaksi: ");
            double totalTransaksi = scanner.nextDouble();
            scanner.nextLine(); // Membersihkan buffer

            // Menyiapkan pernyataan SQL
            String sql = "INSERT INTO transaksi (nama, nomor_hp, total_transaksi) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Mengatur parameter
                preparedStatement.setString(1, nama);
                preparedStatement.setString(2, nomorHp);
                preparedStatement.setDouble(3, totalTransaksi);

                // Menjalankan pernyataan
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data berhasil ditambahkan!");
                } else {
                    System.out.println("Gagal menambahkan data.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tampilkanData(Connection connection) {
        try {
            // Menyiapkan pernyataan SQL
            String sql = "SELECT * FROM transaksi";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                // Menampilkan hasil query
                System.out.println("Data dalam tabel transaksi:");
                while (resultSet.next()) {
                    String nama = resultSet.getString("nama");
                    String nomorHp = resultSet.getString("nomor_hp");
                    double totalTransaksi = resultSet.getDouble("total_transaksi");

                    System.out.println("Nama: " + nama + ", Nomor HP: " + nomorHp + ", Total Transaksi: " + totalTransaksi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void hapusData(Connection connection, String nama) {
        try {
            // Menyiapkan pernyataan SQL DELETE
            String sql = "DELETE FROM transaksi WHERE nama = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Mengatur parameter
                preparedStatement.setString(1, nama);
    
                // Menjalankan pernyataan
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data berhasil dihapus!");
                } else {
                    System.out.println("Data tidak ditemukan atau gagal dihapus.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateData(Connection connection, String nama, double totalTransaksiBaru) {
        try {
            // Menyiapkan pernyataan SQL UPDATE
            String sql = "UPDATE transaksi SET total_transaksi = ? WHERE nama = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Mengatur parameter
                preparedStatement.setDouble(1, totalTransaksiBaru);
                preparedStatement.setString(2, nama);
    
                // Menjalankan pernyataan
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data berhasil diupdate!");
                } else {
                    System.out.println("Data tidak ditemukan atau gagal diupdate.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/tugaspbo";
        String dbuser = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, password);
            System.out.println("Koneksi ke database berhasil!");
            // Jangan lupa untuk menutup koneksi setelah selesai menggunakan
            connection.close();
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database!");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        

        String username = "fadli";
        String pass = "fadli123";
        boolean lgn=false;

        while (!lgn) {
            System.out.print("Masukkan username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Masukkan password: ");
            String inputPassword = scanner.nextLine();

            if (login(username, pass, inputUsername, inputPassword)) {
                String captcha = generateCaptcha();
                System.out.println("CAPTCHA: " + captcha);

                System.out.print("Masukkan CAPTCHA: ");
                String inputCaptcha = scanner.nextLine();

                if (checkCaptcha(captcha, inputCaptcha)) {
                    System.out.println("Login berhasil!");
                    lgn=true;
                } else {
                    System.out.println("Login gagal. CAPTCHA salah.");
                }
            } else {
                System.out.println("Login gagal. Periksa kembali username dan password.");
            }
        }
        Date tanggal = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String tanggalTransaksi = dateFormat.format(tanggal);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");
        String waktuTransaksi = timeFormat.format(tanggal);


        try {
            
            System.out.println("");
            MemberReguler tes1 = new MemberReguler(){};
            System.out.print("Masukkan Nama Pelanggan\t: ");
            tes1.namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan Nomor HP\t: ");
            tes1.noHp = scanner.nextLine();

            System.out.print("Masukkan Alamat\t\t: ");
            tes1.alamat = scanner.nextLine();

            System.out.print("Masukkan Kode Barang\t: ");
            tes1.kodeBarang = scanner.nextLine();

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
            System.out.println("Nama Pelanggan\t:" + tes1.namaPelanggan.toUpperCase());
            System.out.println("Nomor Hp\t:"+tes1.noHp);
            System.out.println("Alamat\t\t:"+tes1.alamat.toLowerCase());
            System.out.println("++++++++++++++++++++++++");
            System.out.println("DATA PEMBELIAN BARANG");
            System.out.println("------------------------");
            System.out.println("Kode Barang\t:" + tes1.kodeBarang.toUpperCase());
            System.out.println("Nama Barang\t:" + tes1.namaBarang);
            System.out.println("Harga Barang\t:" + tes1.hargaBarang);
            System.out.println("Jumlah Beli\t:" + tes1.jumlahBeli);
            System.out.println("TOTAL BAYAR\t:" + tes1.totalBayar);
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Kasir\t\t:" + tes1.namaKasir.toUpperCase());
            System.out.println("");




        } catch (java.util.InputMismatchException e) {
            System.out.println("Maaf, input tidak valid. Pastikan Anda memasukkan nilai numerik untuk harga dan jumlah barang.");
        }

        try (Connection connection = DriverManager.getConnection(url, dbuser, password)) {

            while (true) {
                System.out.println("Pilih operasi:");
                System.out.println("1. Tambah Data");
                System.out.println("2. Tampilkan Data");
                System.out.println("3. Hapus Data");
                System.out.println("4. Update Data");
                System.out.println("5. Keluar");
                System.out.print("Masukkan pilihan Anda: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer

                switch (choice) {
                    case 1:
                        tambahData(connection);
                        break;
                    case 2:
                        tampilkanData(connection);
                        break;
                    case 3:
                        System.out.print("Masukkan nama untuk menghapus data: ");
                        String namaHapus = scanner.nextLine();
                        hapusData(connection, namaHapus);
                        break;
                    case 4:
                        System.out.print("Masukkan nama untuk mengupdate data: ");
                        String namaUpdate = scanner.nextLine();
                    
                        System.out.print("Masukkan total transaksi baru: ");
                        double totalTransaksiBaru = scanner.nextDouble();
                        scanner.nextLine(); // Membersihkan buffer
                    
                        updateData(connection, namaUpdate, totalTransaksiBaru);
                        break;
                    case 5:
                        System.out.println("Program selesai.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // Menutup scanner setelah selesai digunakan
            scanner.close();
        }
    }
}
