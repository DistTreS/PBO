package tugaspbo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pemesanan implements IDatabase{
    String nama;
    String nomorHp;
    String tglReservasi;
    String noKamar;
    Integer durasi;
    Integer total;
    int totalbaru;
    String URL = "jdbc:mysql://localhost:3306/penginapan";
    String USER = "root";
    String PASSWORD = "";
    Scanner scanner = new Scanner(System.in);



    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setNomorHP(String nomor) {
        this.nomorHp = nomor;
    }
    public void setTgl(String tanggal) {
        this.tglReservasi = tanggal;
    }
    public void setNoKmr(String nokmar) {
        this.noKamar = nokmar;
    }
    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    void pesanKamar(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pilih tipe kamar yang ingin dipesan : ");
                    System.out.println("1. Standart");
                    System.out.println("2. VIP");
                    System.out.println("Pilihan : ");
                    int tipe_kamar = scanner.nextInt();
                    if(tipe_kamar == 1){
                        Scanner scanner1 = new Scanner(System.in);

                        System.out.println("");
                        System.out.print("Masukkan nomor kamar yang anda pilih : ");
                        String noKamar = scanner1.nextLine();
                        setNoKmr(noKamar);

                        System.out.print("Masukkan nama pelanggan : ");
                        String nama1 = scanner1.nextLine();
                        setNama(nama1);

                        System.out.print("Masukkan nomor telepon pelanggan : ");
                        String nomorhp1 = scanner1.nextLine();
                        setNomorHP(nomorhp1);

                        System.out.print("Masukkan tanggal reservasi (dd-mm-yyyy): ");
                        String tglReservasi = scanner1.nextLine();
                        setTgl(tglReservasi);

                        System.out.print("Masukkan durasi reservasi (hari) : ");
                        int drsi = scanner1.nextInt();
                        setDurasi(drsi);

                        if(durasi >= 7){
                            this.total=(durasi*150000)*80/100;
                        }
                        else{
                            this.total=(durasi*150000);
                        }

                        System.out.println("Pesanan Berhasil Dibuat");
                        System.out.println("Detail Reservasi");
                        System.out.println("Nomor Kamar : "+this.noKamar);
                        System.out.println("Nama : "+this.nama);
                        System.out.println("Nomor Hp : "+this.nomorHp);
                        System.out.println("Tanggal Reservasi : "+this.tglReservasi);
                        System.out.println("Durasi : "+this.durasi);
                        System.out.println("Total : "+this.total);

                                    }
                    else if(tipe_kamar == 2){
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("");
                        System.out.print("Masukkan nomor kamar yang anda pilih : ");
                        String noKamar = scanner1.nextLine();
                        setNoKmr(noKamar);

                        System.out.print("Masukkan nama pelanggan : ");
                        String nama1 = scanner1.nextLine();
                        setNama(nama1);

                        System.out.print("Masukkan nomor telepon pelanggan : ");
                        String nomorhp1 = scanner1.nextLine();
                        setNomorHP(nomorhp1);

                        System.out.print("Masukkan tanggal reservasi (dd-mm-yyyy): ");
                        String tglReservasi = scanner1.nextLine();
                        setTgl(tglReservasi);

                        System.out.print("Masukkan durasi reservasi (hari) : ");
                        int drsi = scanner1.nextInt();
                        setDurasi(drsi);

                        if(durasi >= 7){
                            this.total=(durasi*250000)*80/100;
                        }
                        else{
                            this.total=(durasi*250000);
                        }

                        System.out.println("Pesanan Berhasil Dibuat");
                        System.out.println("Detail Reservasi");
                        System.out.println("Nomor Kamar : "+this.noKamar);
                        System.out.println("Nama : "+this.nama);
                        System.out.println("Nomor Hp : "+this.nomorHp);
                        System.out.println("Tanggal Reservasi : "+this.tglReservasi);
                        System.out.println("Durasi : "+this.durasi);
                        System.out.println("Total : "+this.total);
                    }else{
                        System.out.println("Berikan Inputan yang benar !");
                    }
                    System.out.println("");
                    try {
                        // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
                        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        String queryEvaluasi = "SELECT * FROM kamar WHERE no_kamar = ?";
                        PreparedStatement preparedStatementEvaluasi = connection.prepareStatement(queryEvaluasi);
                        preparedStatementEvaluasi.setString(1, this.noKamar);
                        ResultSet resultSet = preparedStatementEvaluasi.executeQuery();

                        if (resultSet.next()) {
                            String statusBaru = "Terisi";
                            String queryUpdateStatus = "UPDATE kamar SET status = ? WHERE no_kamar = ?";
                            PreparedStatement preparedStatementUpdateStatus = connection.prepareStatement(queryUpdateStatus);
                            preparedStatementUpdateStatus.setString(1, statusBaru);
                            preparedStatementUpdateStatus.setString(2, this.noKamar);

                            // Eksekusi query update
                            int hasil = preparedStatementUpdateStatus.executeUpdate();
                            if (hasil > 0) {
                                System.out.println("Status kamar berhasil diubah.");
                            } else {
                                System.out.println("Gagal mengubah status kamar.");
                            }

                            preparedStatementEvaluasi.close();
                            preparedStatementUpdateStatus.close();
                        } else {
                            System.out.println("Data kamar dengan nomor " + this.noKamar + " tidak ditemukan.");
                        }
                        preparedStatementEvaluasi.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

    }

    @Override
    public void inputDataPelanggan() {
        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk menyimpan data pelanggan ke database
            String query = "INSERT INTO pelanggan (nama, nomor_hp, tanggal_reservasi, no_kamar, durasi, total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Menyetel parameter query dengan nilai-nilai dari objek Pemesanan
            preparedStatement.setString(1, this.nama);
            preparedStatement.setString(2, this.nomorHp);
            preparedStatement.setString(3, this.tglReservasi);
            preparedStatement.setString(4, this.noKamar);
            preparedStatement.setInt(5, this.durasi);
            preparedStatement.setInt(6, this.total);

            // Eksekusi query
            preparedStatement.executeUpdate();

            // Menutup koneksi
            preparedStatement.close();
            connection.close();

            System.out.println("Data pelanggan berhasil disimpan ke database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cekKamarTersedia(){

        try {
            // Membuat koneksi ke database
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mendapatkan nomor kamar yang tersedia
            String query = "SELECT no_kamar FROM kamar WHERE status = 'Tersedia'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Eksekusi query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Menampilkan nomor kamar yang tersedia
            System.out.println("Nomor Kamar Tersedia:");
            while (resultSet.next()) {
                String nomorKamar = resultSet.getString("no_kamar");
                System.out.println("- " + nomorKamar);
            }

            // Menutup koneksi
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cekKamar(){
            try {
                // Membuat koneksi ke database
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
    
                // Query untuk mendapatkan semua data kamar
                String query = "SELECT no_kamar, harga_permalam, status FROM kamar";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
    
                // Eksekusi query
                ResultSet resultSet = preparedStatement.executeQuery();
    
                // Menampilkan data kamar
                System.out.println("Data Kamar:");
                while (resultSet.next()) {
                    String nomorKamar = resultSet.getString("no_kamar");
                    int harga = resultSet.getInt("harga_permalam");
                    String status = resultSet.getString("status");
    
                    System.out.println("Nomor Kamar: " + nomorKamar + ", Harga: " + harga + ", Status: " + status);
                }
    
                // Menutup koneksi
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void updatePesanan() {

        System.out.print("Masukkan nama pelanggan yang ingin diupdate: ");
        String namaPelanggan = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM pelanggan WHERE nama = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, namaPelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama dan minta input data baru
                System.out.println("Data pelanggan yang ditemukan:");
                System.out.println("Nama: " + resultSet.getString("nama"));
                System.out.println("Nomor HP: " + resultSet.getString("nomor_hp"));
                System.out.println("Tanggal Reservasi: " + resultSet.getString("tanggal_reservasi"));
                System.out.println("Nomor Kamar: " + resultSet.getString("no_kamar"));
                System.out.println("Durasi: " + resultSet.getInt("durasi") + " malam");
                System.out.println("Total: Rp. " + resultSet.getInt("total"));


                // Minta input data baru
                System.out.println("Masukkan data baru:");

                // Input untuk setiap atribut yang diizinkan diubah
                System.out.print("Masukkan tanggal reservasi baru: ");
                String tanggalReservasiBaru = scanner.nextLine();

                System.out.print("Masukkan durasi baru (jumlah malam): ");
                int durasiBaru = scanner.nextInt();

                System.out.println("Masukkan type kamar berdasarkan nomor kamar !");
                System.out.println("kode awal 'S' untuk kamar standart dan kode awal 'V' untuk kamar VIP");
                System.out.println("pilihan : ");
                System.out.println("1. Standart");
                System.out.println("2. VIP");
                int pilih=scanner.nextInt();
                if (pilih==1) {
                    if(durasiBaru >= 7){
                            totalbaru=(durasiBaru*150000)*80/100;
                        }
                        else{
                            totalbaru=(durasiBaru*150000);
                        }
                }
                else if (pilih==2) {
                    if(durasiBaru >= 7){
                            totalbaru=(durasiBaru*250000)*80/100;
                        }
                        else{
                            totalbaru=(durasiBaru*250000);
                        }
                }else{
                    System.out.println("Maaf, pilihan anda tidak valid.");
                }

                // Query UPDATE untuk mengubah tanggal_reservasi dan durasi
                String queryUpdate = "UPDATE pelanggan SET tanggal_reservasi = ?, durasi = ?, total = ? WHERE nama = ?";
                PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate);
                preparedStatementUpdate.setString(1, tanggalReservasiBaru);
                preparedStatementUpdate.setInt(2, durasiBaru);
                preparedStatementUpdate.setInt(3, totalbaru);
                preparedStatementUpdate.setString(4, namaPelanggan);

                // Eksekusi query update
                preparedStatementUpdate.executeUpdate();

                System.out.println("Data pelanggan berhasil diupdate.");

                // Menutup koneksi
                preparedStatementCari.close();
                preparedStatementUpdate.close();
                connection.close();
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void hapusPesanan() {
        System.out.print("Masukkan nama pelanggan yang ingin dihapus: ");
        String namaPelanggan = scanner.nextLine();


        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM pelanggan WHERE nama = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, namaPelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama
                System.out.println("Data pelanggan yang akan dihapus:");
                System.out.println("Nama: " + resultSet.getString("nama"));
                System.out.println("Tanggal Reservasi: " + resultSet.getString("tanggal_reservasi"));
                System.out.println("Durasi: " + resultSet.getInt("durasi") + " malam");
                String nokmarstat=resultSet.getString("no_kamar");

                // Konfirmasi pengguna
                System.out.print("Apakah Anda yakin ingin menghapus pesanan ini? (y/n): ");
                String konfirmasi = scanner.nextLine().toLowerCase();

                if (konfirmasi.equals("y")) {
                    // Jika pengguna mengonfirmasi, lakukan penghapusan
                    String queryHapus = "DELETE FROM pelanggan WHERE nama = ?";
                    PreparedStatement preparedStatementHapus = connection.prepareStatement(queryHapus);
                    preparedStatementHapus.setString(1, namaPelanggan);

                    String statusBaru = "Tersedia";
                    String queryUpdateStatus = "UPDATE kamar SET status = ? WHERE no_kamar = ?";
                    PreparedStatement preparedStatementUpdateStatus = connection.prepareStatement(queryUpdateStatus);
                    preparedStatementUpdateStatus.setString(1, statusBaru);
                    preparedStatementUpdateStatus.setString(2, nokmarstat);

                    // Eksekusi query hapus
                    preparedStatementHapus.executeUpdate();
                    preparedStatementUpdateStatus.execute();

                    System.out.println("Data pelanggan berhasil dihapus.");
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }

                // Menutup koneksi
                preparedStatementCari.close();
                connection.close();
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void cekPelanggan() {
        System.out.print("Masukkan nama pelanggan yang ingin di Cek: ");
        String namaPelanggan = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM pelanggan WHERE nama = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, namaPelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama dan minta input data baru
                System.out.println("Data pelanggan yang ditemukan:");
                System.out.println("Nama: " + resultSet.getString("nama"));
                System.out.println("Nomor HP: " + resultSet.getString("nomor_hp"));
                System.out.println("Tanggal Reservasi: " + resultSet.getString("tanggal_reservasi"));
                System.out.println("Nomor Kamar: " + resultSet.getString("no_kamar"));
                System.out.println("Durasi: " + resultSet.getInt("durasi") + " malam");
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } 

}
