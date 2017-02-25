/**
 * @author mohammad.k.hader on 2/24/2016.
 * The class (model) that contains the attributes for JSON API 
 */
package servlet;


public class HotelInformation{
    private String dest,tripDate,ratings,imgPath,description,hotelName,price;
	public HotelInformation(){}
	public String getPrice() {
        return price;
    }
	public void setPrice(String price) {
        this.price = price;
    }
	public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}