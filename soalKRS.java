import java.util.Scanner;

public class soalKRS {
    static String[][] data = new String[24][5];
    static int jmlData = 0;
    static Scanner sc = new Scanner(System.in);

    public static void tambahDataKRS() {
        System.out.println("\n--- Tambah Data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = sc.nextLine();
        System.out.print("NIM: ");
        String nim = sc.nextLine();
        int totalSKS = 0;

        while (true) {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMatkul = sc.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMatkul = sc.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = sc.nextInt();
            sc.nextLine();

            if (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS tidak valid! SKS harus antara 1 dan 3.");
                continue;
            } else if (totalSKS + sks > 24) {
                System.out.println("Total SKS melebihi 24! Data tidak bisa ditambahkan.");
                break;
            }

            data[jmlData][0] = nama;
            data[jmlData][1] = nim;
            data[jmlData][2] = kodeMatkul;
            data[jmlData][3] = namaMatkul;
            data[jmlData][4] = String.valueOf(sks);
            jmlData++;

            totalSKS += sks;
            System.out.println("Data mata kuliah berhasil ditambahkan.");

            System.out.print("Tambah mata kuliah lain? (y/t): ");
            String pilihan = sc.nextLine();
            if (pilihan.equalsIgnoreCase("t"))
            break;
        }

        System.out.println("Total SKS yang diambil: " + totalSKS + "\n");
    }

    public static void tampilkanDaftarKRS() {
        System.out.println("\n--- Tampilkan Daftar KRS Mahasiswa ---");
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = sc.nextLine();
        int totalSKS = 0;

        System.out.println("Daftar KRS:");
        System.out.printf("%-10s %-20s %-10s %-30s %-10s\n", "NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS");

        for (int i = 0; i < jmlData; i++) {
            if (data[i][1].equals(nim)) {
                System.out.printf("%-10s %-20s %-10s %-30s %-10s\n", data[i][1], data[i][0], data[i][2], data[i][3], data[i][4]);
                totalSKS += Integer.parseInt(data[i][4]);
            }
        }

        if (totalSKS == 0) {
            System.out.println("Tidak ada data KRS untuk NIM tersebut.");
        } else {
            System.out.println("Total SKS: " + totalSKS + "\n");
        }
    }

    public static void analisisDataKRS() {
        System.out.println("\n--- Analisis Data KRS ---");
        int jmlMhsKrg20 = 0;
        boolean[] mahasiswaDihitung = new boolean[jmlData];

        for (int i = 0; i < jmlData; i++) {
            String nim = data[i][1];

            if (!mahasiswaDihitung[i]) {
                int totalSKS = 0;

                for (int j = 0; j < jmlData; j++) {
                    if (data[j][1].equals(nim)) {
                        totalSKS += Integer.parseInt(data[j][4]);
                    }
                }

                if (totalSKS < 20) {
                    jmlMhsKrg20++;
                }

                for (int x = 0; x < jmlData; x++) {
                    if (data[x][1].equals(nim)) {
                        mahasiswaDihitung[x] = true;
                    }
                }
            }
        }
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jmlMhsKrg20 + "\n");
    }
    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine();

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
}