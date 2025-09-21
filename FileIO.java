package fileio;

import entity.Reservation;
import entitylist.ReservationList;

import java.io.*;
import java.util.List;

public class FileIO {
    private static final String FILE_PATH = "textfile/reservations.txt";

    public static void saveReservations(ReservationList reservationList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reservation reservation : reservationList.getReservations()) {
                writer.write(reservation.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadReservations(ReservationList reservationList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    Reservation reservation = new Reservation(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
                    reservationList.addReservation(reservation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}