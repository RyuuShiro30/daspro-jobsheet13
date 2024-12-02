import java.util.ArrayList;
import java.util.Scanner;

class MataKuliah {
    String kodeMataKuliah;
    String namaMataKuliah;
    int sks;

    MataKuliah(String kodeMataKuliah, String namaMataKuliah, int sks) {
        this.kodeMataKuliah = kodeMataKuliah;
        this.namaMataKuliah = namaMataKuliah;
        this.sks = sks;
    }
}

class Mahasiswa {
    String nama;
    String nim;
    ArrayList<MataKuliah> mataKuliahList = new ArrayList<>();

    Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    void tambahMataKuliah(MataKuliah mataKuliah) {
        mataKuliahList.add(mataKuliah);
    }

    int totalSKS() {
        int total = 0;
        for (MataKuliah mk : mataKuliahList) {
            total += mk.sks;
        }
        return total;
    }
}

public class KRS {
    static ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1:
                    tambahDataKRS();
                    break;
                case 2:
                    tampilkanDaftarKRS();
                    break;
                case 3:
                    analisisDataKRS();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
            System.out.println();
        } while (pilihan != 4);
    }

    static String tambahDataKRS() {
        System.out.println("--- Tambah Data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(nama, nim);

        boolean tambahLagi;
        do {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMataKuliah = scanner.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMataKuliah = scanner.nextLine();

            int sks;
            while (true) {
                System.out.print("Jumlah SKS (1-3): ");
                sks = scanner.nextInt();
                scanner.nextLine();
                if (sks >= 1 && sks <= 3) {
                    break;
                } else {
                    System.out.println("Jumlah SKS tidak valid! SKS harus antara 1 dan 3.");
                    System.out.println("Silakan masukkan ulang mata kuliah.");
                    System.out.print("Kode Mata Kuliah: ");
                    kodeMataKuliah = scanner.nextLine();
                    System.out.print("Nama Mata Kuliah: ");
                    namaMataKuliah = scanner.nextLine();
                }
            }

            MataKuliah mataKuliah = new MataKuliah(kodeMataKuliah, namaMataKuliah, sks);
            mahasiswa.tambahMataKuliah(mataKuliah);
            System.out.println("Data mata kuliah berhasil ditambahkan.");

            System.out.print("Tambah mata kuliah lain? (y/t): ");
            tambahLagi = scanner.nextLine().equalsIgnoreCase("y");

        } while (tambahLagi);

        mahasiswaList.add(mahasiswa);

        System.out.println("Total SKS yang diambil: " + mahasiswa.totalSKS());
        return nim;
    }

    static void tampilkanDaftarKRS() {
        System.out.println("--- Tampilkan Daftar KRS Mahasiswa");
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();

        for (Mahasiswa mhs : mahasiswaList) {
            if (mhs.nim.equals(nim)) {
                System.out.println("--- Tampilkan Daftar KRS Mahasiswa ---");
                System.out.printf("%-10s %-20s %-10s %-30s %-10s\n", "NIM", "Nama", "Kode MK", "Nama Mata Kuliah",
                        "SKS");
                for (MataKuliah mk : mhs.mataKuliahList) {
                    System.out.printf("%-10s %-20s %-10s %-30s %-10d\n", mhs.nim, mhs.nama, mk.kodeMataKuliah,
                            mk.namaMataKuliah, mk.sks);
                }
                System.out.println("Total SKS yang diambil: " + mhs.totalSKS());
                return;
            }
        }
        System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
    }

    static void analisisDataKRS() {
        int jumSKSkurang20 = 0;
        for (Mahasiswa mhs : mahasiswaList) {
            if (mhs.totalSKS() < 20) {
                jumSKSkurang20++;
            }
        }
        System.out.println("--- Analisis Data KRS ---");
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jumSKSkurang20);
    }
}