package entity;

public class Reservation {
    private String name;
    private String nid;
    private String mobile;
    private String date;
    private String train;
    private String travelClass;
    private String from;
    private String to;

    public Reservation(String name, String nid, String mobile, String date, String train, String travelClass, String from, String to) {
        this.name = name;
        this.nid = nid;
        this.mobile = mobile;
        this.date = date;
        this.train = train;
        this.travelClass = travelClass;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public String getNid() {
        return nid;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDate() {
        return date;
    }

    public String getTrain() {
        return train;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toFileString() {
        return name + "," + nid + "," + mobile + "," + date + "," + train + "," + travelClass + "," + from + "," + to;
    }
}