package restfulAPI.CRUD.misc.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class bookingresponse {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private bookingid booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public bookingid getBooking() {
        return booking;
    }

    public void setBooking(bookingid booking) {
        this.booking = booking;
    }

}