package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        new File("output").mkdirs();
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        //SCRIERE
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            raf.setLength(0);
            for (int i=0; i<n; i++) {
                String[] p = sc.nextLine().split(" ");

                int id = Integer.parseInt(p[0]);
                double suma = Double.parseDouble(p[1]);
                String data = p[2];
                TipTranzactie tip = TipTranzactie.valueOf(p[3]);

                raf.seek(i * RECORD_SIZE);

                raf.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array());
                raf.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(suma).array());

                byte[] dateBytes = new byte[10];
                byte[] raw = data.getBytes();
                System.arraycopy(raw, 0, dateBytes, 0, Math.min(raw.length, 10));
                raf.write(dateBytes);

                raf.writeByte(tip == TipTranzactie.CREDIT ? 0 : 1);
                raf.writeByte(0);    //PENDING

                raf.write(new byte[8]);  //padding
            }
        }

        //COMENZI
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] cmd = line.split(" ");
                switch (cmd[0]) {
                    case "READ": {
                        int idx = Integer.parseInt(cmd[1]);
                        System.out.println(readRecord(raf, idx));
                        break;
                    }
                    case "UPDATE": {
                        int idx = Integer.parseInt(cmd[1]);
                        String statusStr = cmd[2];

                        byte status = switch (statusStr) {
                            case "PENDING" -> 0;
                            case "PROCESSED" -> 1;
                            case "REJECTED" -> 2;
                            default -> 0;
                        };

                        raf.seek(idx * RECORD_SIZE + 23);
                        raf.writeByte(status);

                        System.out.println("Updated [" + idx + "]: " + statusStr);
                        break;
                    }
                    case "PRINT_ALL": {
                        long size = raf.length() / RECORD_SIZE;
                        for (int i = 0; i < size; i++) {
                            System.out.println(readRecord(raf, i));
                        }
                        break;
                    }
                }
            }
        }
    }

    private static String readRecord(RandomAccessFile raf, int idx) throws IOException {
        raf.seek(idx * RECORD_SIZE);
        byte[] buffer = new byte[RECORD_SIZE];
        raf.readFully(buffer);

        ByteBuffer bb = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);

        int id = bb.getInt();
        double suma = bb.getDouble();

        byte[] dateBytes = new byte[10];
        bb.get(dateBytes);
        String data = new String(dateBytes).trim();

        byte tip = bb.get();
        byte status = bb.get();

        String tipStr = tip == 0 ? "CREDIT" : "DEBIT";

        String statusStr = switch (status) {
            case 0 -> "PENDING";
            case 1 -> "PROCESSED";
            case 2 -> "REJECTED";
            default -> "UNKNOWN";
        };

        return "[" + idx + "] id=" + id +
                " data=" + data +
                " tip=" + tipStr +
                " suma=" + String.format(Locale.US, "%.2f", suma) +
                " RON status=" + statusStr;
    }
}