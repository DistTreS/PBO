package tugaspbo;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Date tanggal = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String tanggalTransaksi = dateFormat.format(tanggal);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");
        String waktuTransaksi = timeFormat.format(tanggal);

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
        int pilihan = 0;
        while (pilihan != 10) {
            Penginapan penginapan1 = new Penginapan();
            Pemesanan pemesanan1= new Pemesanan();

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Selamat Datang di Penginapan Keluarga Fadli !");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("=============================================");
            System.out.println("Tanggal \t:" + tanggalTransaksi);
            System.out.println("Waktu \t\t:" + waktuTransaksi);
            System.out.println("=============================================");
            System.out.println("Menu:");
            System.out.println("1. Detail Layanan");
            System.out.println("2. Pesan Kamar");
            System.out.println("3. Cek Kamar");
            System.out.println("4. Update Pesanan");
            System.out.println("5. Hapus Pesanan");
            System.out.println("6. Cek Pelanggan");
            System.out.println("7. Tambahkan Kamar");
            System.out.println("8. Hapus Kamar");
            System.out.println("9. Pesan Makanan ke kamar");
            System.out.println("10. Tutup");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            try {
                System.out.print("Pilih menu (1-10): ");
                pilihan = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Masukkan hanya bilangan bulat.");
            }

            switch (pilihan) {
                case 1:
                    penginapan1.detailLayanan();
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("Berikut daftar kamar yang tersedia Silahkan Pilih dari kamar berikut !");
                    pemesanan1.cekKamarTersedia();
                    System.out.println("");
                    System.out.println("keterangan : kode awal 'S' untuk kamar standart dan kode awal 'V' untuk kamar VIP");
                    System.out.println("");
                    pemesanan1.pesanKamar();
                    pemesanan1.inputDataPelanggan();
                    System.out.println("");
                    break;
                case 3:
                    pemesanan1.cekKamar();
                    System.out.println("");
                    break;
                case 4:
                    pemesanan1.updatePesanan();
                    System.out.println("");
                    break;
                case 5:
                    pemesanan1.hapusPesanan();
                    System.out.println("");
                    break;
                case 6:
                    pemesanan1.cekPelanggan();
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("");
                    System.out.println("Pilih tipe kamar yang ingin ditambahkan : ");
                    System.out.println("1. Standart");
                    System.out.println("2. VIP");
                    System.out.println("3. Batal");
                    System.out.println("Pilihan : ");
                    int jenis_kamar = scanner.nextInt();
                    if(jenis_kamar == 1){
                        Standart baru = new Standart();
                        baru.TambahkmarStandart();
                    }
                    else if(jenis_kamar == 2){
                        Vip baruVIP = new Vip();
                        baruVIP.TambahkmarVip();
                    }
                    else if(jenis_kamar == 3){
                        break;
                    }
                    else{
                        System.out.println("Berikan Inputan yang benar !");
                    }
                    System.out.println("");
                    break;
                case 8:
                    penginapan1.hapusKamar();
                    System.out.println("");
                    break;
                case 9:
                    Vip vip1=new Vip();
                    vip1.pesanMakanan();
                    break;
                case 10:
                    System.out.println("Terima kasih, sampai jumpa!");
                    System.out.println("");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }
    }

    private static boolean login(String correctUsername, String correctPassword, String inputUsername, String inputPassword) {
        return correctUsername.equals(inputUsername) && correctPassword.equals(inputPassword);
    }

    private static String generateCaptcha() {
        return "pM3k74L";
    }

    private static boolean checkCaptcha(String correctCaptcha, String inputCaptcha) {
        return correctCaptcha.equalsIgnoreCase(inputCaptcha);
    }
}
