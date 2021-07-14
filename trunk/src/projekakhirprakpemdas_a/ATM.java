/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekakhirprakpemdas_a;

import java.util.Scanner;

/**
 *
 * @author Sayoga
 */
public class ATM {

    static Scanner input = new Scanner(System.in);
    static String[][] listAkun = new String[5][5];

    public static void main(String[] args) {
        setAkunAwal();

        switch (menuAwal()) {
            case 1: {
                prosesDaftar();
                break;
            }
            case 2: {
                String cookie = prosesLogin();
                while (cookie != null) {
                    switch (menuLogin()) {
                        case 1: {
                            informasiSaldo(cookie);
                            System.out.println("");
                            break;
                        }
                        case 2: {
                            tarikTunai(cookie);
                            System.out.println("");
                            break;
                        }
                        case 3: {
                            prosesTransfer(cookie);
                            System.out.println("");
                            break;
                        }
                        case 4: {
                            gantiPin(cookie);
                            System.out.println("");
                            break;
                        }
                        case 5: {
                            cookie = null;
                            break;
                        }
                        default: {
                            System.out.println("Pilihan tidak tersedia");
                            System.out.println("");
                        }
                    }
                }
                break;
            }
            default: {
                System.out.println("Pilihan tidak tersedia");
            }
        }
    }

    public static void setAkunAwal() {
        listAkun[0][0] = "Okza";
        listAkun[0][1] = "123456";
        listAkun[0][2] = "ABC";
        listAkun[0][3] = "00000000";
        listAkun[0][4] = "500000";

        listAkun[1][0] = "Farhan";
        listAkun[1][1] = "234567";
        listAkun[1][2] = "ABC";
        listAkun[1][3] = "00000001";
        listAkun[1][4] = "2000000";

        listAkun[2][0] = "Syarief";
        listAkun[2][1] = "345678";
        listAkun[2][2] = "Diriman";
        listAkun[2][3] = "11111110";
        listAkun[2][4] = "1500000";

        listAkun[3][0] = "Titus";
        listAkun[3][1] = "456789";
        listAkun[3][2] = "IBN";
        listAkun[3][3] = "22222202";
        listAkun[3][4] = "700000";

        listAkun[4][0] = "Darell";
        listAkun[4][1] = "567890";
        listAkun[4][2] = "IRB";
        listAkun[4][3] = "33333033";
        listAkun[4][4] = "100000000";
    }

    public static int menuAwal() {
        int pil;
        System.out.println("Selamat datang program ATM");
        System.out.println("1. Daftar Rekening");
        System.out.println("2. Masuk");
        System.out.print("Masukkan pilihan: ");
        pil = input.nextInt();
        input.nextLine();
        System.out.println("");
        return pil;
    }

    public static int menuLogin() {
        System.out.println("Menu:");
        System.out.printf("1. %s\n", "Informasi Saldo");
        System.out.printf("2. %s\n", "Penarikan Tunai");
        System.out.printf("3. %s\n", "Transfer");
        System.out.printf("4. %s\n", "Ganti PIN");
        System.out.printf("5. %s\n", "Keluar");
        System.out.print("Masukkan pilihan: ");
        int pil = input.nextInt();
        input.nextLine();
        return pil;
    }

    public static int menuTransfer() {
        System.out.printf("1. %s\n", "Transfer sesama bank");
        System.out.printf("2. %s\n", "Transfer beda bank");
        System.out.printf("3. %s\n", "Kembali");
        System.out.print("Masukkan pilihan: ");
        int pil = input.nextInt();
        input.nextLine();
        return pil;
    }

    public static void prosesDaftar() {
        System.out.printf("%-20s: ", "Masukkan username");
        String username = input.nextLine();
        if (!isThere(username, 0)) {
            System.out.printf("%-20s: ", "Masukkan Pin");
            String pin = input.nextLine();
            System.out.printf("%-20s: ", "Masukkan Bank");
            String bank = input.nextLine();
            System.out.printf("%-20s: ", "Nomor rekening");
            String noRek = input.nextLine();
            System.out.printf("%-20s: ", "Masukkan saldo awal");
            String saldo = input.nextLine();
            daftar(username, pin, bank, noRek, saldo);
            System.out.println("Pendaftaran Berhasil");
        } else {
            System.out.println("Tidak berhasil mendaftar");
            System.out.println("Username telah terpakai");
        }
    }

    public static void daftar(String username, String pin, String bank,
            String noRek, String saldo) {
        String[][] newListAkun = new String[listAkun.length + 1][5];
        String[] newAkun = {username, pin, bank, noRek, saldo};
        int i;

        for (i = 0; i < listAkun.length; i++) {
            newListAkun[i] = listAkun[i];
        }
        newListAkun[i] = newAkun;

        listAkun = newListAkun;
    }

    public static String prosesLogin() {
        String cookie = null;
        System.out.printf("%-20s: ", "Masukkan username");
        String username = input.nextLine();
        System.out.printf("%-20s: ", "Masukkan pin");
        String pin = input.nextLine();
        if (isThere(username, 0)) {
            if (listAkun[indexAkun(username, 0)][1].equals(pin)) {
                cookie = username;
            } else {
                System.out.println("Pin salah");
            }
        } else {
            System.out.println("Username salah");
        }
        return cookie;
    }

    public static boolean isThere(String searched, int indexSearched) {
        boolean there = false;
        for (int i = 0; i < listAkun.length; i++) {
            if (searched.equals(listAkun[i][indexSearched])) {
                there = true;
            }
        }
        return there;
    }

    public static int indexAkun(String searched, int indexSearched) {
        int i;
        for (i = 0; i < listAkun.length; i++) {
            if (listAkun[i][indexSearched].equals(searched)) {
                break;
            }
        }
        return i;
    }

    public static void informasiSaldo(String username) {
        System.out.println("Jumlah Saldo yang dimiliki sebanyak");
        System.out.printf("Rp %s\n", listAkun[indexAkun(username, 0)][4]);
    }

    public static void tarikTunai(String username) {
        System.out.println("Masukkan jumlah uang yang ingin ditarik:");
        int nominal = input.nextInt();
        int saldo = Integer.parseInt(listAkun[indexAkun(username, 0)][4]);
        if (nominal <= saldo - 50000) {
            saldo -= nominal;
            listAkun[indexAkun(username, 0)][4] = String.valueOf(saldo);
            System.out.println("Silahkan ambil uang anda");
            System.out.printf("-----Rp %d-----\n", nominal);
        } else if (nominal < saldo) {
            System.out.println("Saldo di bank harus tersisa Rp 50000");
        } else {
            System.out.println("Saldo tidak cukup");
        }
    }

    public static void gantiPin(String username) {
        System.out.print("Masukkan pin lama: ");
        String oldPin = input.nextLine();
        System.out.print("Masukkan pin baru: ");
        String newPin = input.nextLine();
        if (listAkun[indexAkun(username, 0)][1].equals(oldPin)) {
            listAkun[indexAkun(username, 0)][1] = newPin;
            System.out.println("Berhasil mengganti pin");
        } else {
            System.out.println("Pin salah");
        }
    }

    public static void prosesTransfer(String username) {
        boolean repeat = true;
        do {
            switch (menuTransfer()) {
                case 1: {
                    System.out.println("Tranfer Sesama Bank");
                    System.out.println("Masukkan nomor rekening tujuan: ");
                    String noRekTujuan = input.nextLine();
                    System.out.println("Masukkan jumlah uang yang ingin di transfer: ");
                    int nominal = input.nextInt();
                    int saldoTujuan = Integer.parseInt(listAkun[indexAkun(noRekTujuan, 3)][4]);
                    int saldo = Integer.parseInt(listAkun[indexAkun(username, 0)][4]);
                    System.out.println("");
                    transfer(noRekTujuan, saldoTujuan, username, saldo, nominal, 0);
                    break;
                }
                case 2: {
                    System.out.println("Transfer Beda Bank");
                    System.out.println("Masukkan nomor rekening tujuan: ");
                    String noRekTujuan = input.nextLine();
                    System.out.println("Masukkan jumlah uang yang ingin di transfer: ");
                    int nominal = input.nextInt();
                    int saldoTujuan = Integer.parseInt(listAkun[indexAkun(noRekTujuan, 3)][4]);
                    int saldo = Integer.parseInt(listAkun[indexAkun(username, 0)][4]);
                    System.out.println("Tranfer beda bank");
                    System.out.println("");
                    transfer(noRekTujuan, saldoTujuan, username, saldo, nominal, 6500);
                    break;
                }
                case 3: {
                    repeat = false;
                    break;
                }
                default: {
                    System.out.println("Pilihan tidak ada");
                }
            }
            System.out.println("");
        } while (repeat);
    }

    public static void transfer(String noRekTujuan, int saldoTujuan, String username, int saldoAwal, int nominal, int tax) {
        if (isThere(noRekTujuan, 3)) {
            if (nominal <= saldoAwal - 50000 - tax) {
                saldoTujuan = Integer.parseInt(listAkun[indexAkun(noRekTujuan, 3)][4]);
                saldoAwal -= tax;
                saldoAwal -= nominal;
                saldoTujuan += nominal;

                listAkun[indexAkun(username, 0)][4] = String.valueOf(saldoAwal);
                listAkun[indexAkun(noRekTujuan, 3)][4] = String.valueOf(saldoTujuan);

                System.out.printf("%s\n", "Transfer berhasil");
                System.out.printf("%-15s: %s\n", "Rekening tujuan", noRekTujuan);
                System.out.printf("%-15s: Rp. %d\n", "Nominal", nominal);
                System.out.printf("%-15s: Rp. %d\n", "Biaya admin", tax);
                System.out.printf("%-15s: Rp. %d\n", "Sisa Saldo", saldoAwal);
            } else if (nominal < saldoAwal) {
                System.out.println("Saldo di bank harus tersisa Rp 50000");
            } else {
                System.out.println("Saldo tidak cukup");
            }
            for (String item : listAkun[indexAkun(noRekTujuan, 3)]) {
                System.out.println(item);
            }
        } else {
            System.out.println("Nomor Rekening tujuan tidak terdaftar");
        }
    }

}
