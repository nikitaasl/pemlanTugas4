import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<Pelanggan> daftarPelanggan = new ArrayList<>();
        daftarPelanggan.add(new Pelanggan("381234538", 500000, "Andi", "1234"));
        daftarPelanggan.add(new Pelanggan("561234556", 200000, "Budi", "4567"));
        daftarPelanggan.add(new Pelanggan("741234574", 1000000, "Citra", "8910"));

        while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. Pembelian");
        System.out.println("2. Top-up");
        System.out.println("3. Keluar");
        System.out.println("Pilih menu");
        int pilihan = scanner.nextInt();

        if (pilihan == 3) break;
        System.out.println("Masukkan nomor pelanggan");
        String nomor = scanner.next();
        
        Pelanggan pelangganTerpilih = null;
        for(Pelanggan p : daftarPelanggan) {
            if (p.getNomorPelanggan().equals(nomor)){
                pelangganTerpilih = p;
                break;
            }
        }

        if (pelangganTerpilih == null) {
            System.out.println("Nomor pelanggan tidak ditemukan.");
            continue;
        }
        System.out.println("Masukkan PIN: ");
        String pin = scanner.next();

        if (!pelangganTerpilih.verifikasiPin(pin)) {
            System.out.println("PIN salah! ");
            continue;
        }

        System.out.println("Masukkan jumlah: ");
        double jumlah = scanner.nextDouble();

        if (pilihan == 1) {
            TransaksiPengguna.lakukanPembelian(pelangganTerpilih, jumlah);
        }else if (pilihan == 2) {
            TransaksiPengguna.topUp(pelangganTerpilih, jumlah);
        }
        }
         scanner.close();   
        }
    }

