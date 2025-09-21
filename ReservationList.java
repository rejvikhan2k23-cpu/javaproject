package entitylist;

import entity.Reservation;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ReservationList {
    private List<Reservation> reservations;
    private DefaultTableModel tableModel;

    public ReservationList() {
        reservations = new ArrayList<>();
        String[] columns = {"Name", "NID", "Mobile", "Date", "Train", "Class", "From", "To"};
        tableModel = new DefaultTableModel(columns, 0);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        Object[] row = {
            reservation.getName(),
            reservation.getNid(),
            reservation.getMobile(),
            reservation.getDate(),
            reservation.getTrain(),
            reservation.getTravelClass(),
            reservation.getFrom(),
            reservation.getTo()
        };
        tableModel.addRow(row);
    }

    public void removeReservation(int index) {
        if (index >= 0 && index < reservations.size()) {
            reservations.remove(index);
            tableModel.removeRow(index);
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getReservationCount() {
        return reservations.size();
    }
}