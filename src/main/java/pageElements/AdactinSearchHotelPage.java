package pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

public class AdactinSearchHotelPage extends BasePage {

    public AdactinSearchHotelPage() {
        PageFactory.initElements(driver,this);
    }

    SeleniumUtils seleniumUtils = new SeleniumUtils();


    @FindBy(id = "location")
    WebElement locationBtn;

    @FindBy(id = "hotels")
    WebElement hotelsBtn;

    @FindBy(id = "room_type")
    WebElement roomTypeBtn;

    @FindBy(id = "room_nos")
    WebElement roomNoBtn;

    @FindBy(id = "datepick_in")
    WebElement datePickInField;

    @FindBy(id = "datepick_out")
    WebElement dateCheckoutField;

    @FindBy(id = "adult_room")
    WebElement adultsPerRoomBtn;

    @FindBy(id = "Submit")
    WebElement searchBtn;

    @FindBy(id = "radiobutton_0")
    WebElement confirmRadioBtn;

    @FindBy(id = "continue")
    WebElement continueBtn;


    public void selectLocation(Location location) {
        seleniumUtils.selectByVisibleText(locationBtn, location.getLocation());
    }

    public  enum Location {
        SYDNEY("Sydney"),
        MELBOURNE("Melbourne"),
        BRISBANE("Brisbane");

        String location;
        Location(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }
    }

    public void selectHotel(Hotels hotel) {
        seleniumUtils.selectByValue(hotelsBtn, hotel.getHotel());
    }

    public enum Hotels {
        CREEK("Hotel Creek"),
        SUNSHINE("Hotel Sunshine");

        String hotel;
        Hotels(String hotel){
            this.hotel = hotel;
        }

        public String getHotel() {
            return hotel;
        }
    }

    public void selectRoomType(RoomType roomType) {
        seleniumUtils.selectByValue(roomTypeBtn,roomType.getRoomType());
    }

    public enum RoomType {
        STANDARD("Standard"),
        DELUXE("Deluxe");

        String roomType;
        RoomType(String roomType){
            this.roomType = roomType;
        }

        public String getRoomType(){
            return roomType;
        }
    }

    public void selectNoOfRooms(int noOfRooms) {
        String no = Integer.toString(noOfRooms);
        seleniumUtils.selectByValue(roomNoBtn,no);
    }

    public void enterCheckInDate(String cinDate){
        seleniumUtils.setText(datePickInField,cinDate);
    }

    public void enterCheckOutDate(String cOutDate){
        seleniumUtils.setText(dateCheckoutField,cOutDate);
    }

    public void enterTheNoOfAdults(int noOfAdults) {
        seleniumUtils.selectByValue(adultsPerRoomBtn,Integer.toString(noOfAdults));
    }

    public void clickSearch() {
        seleniumUtils.click(searchBtn);
    }

    public void confirmAndContinue() {
        confirmRadioBtn.click();
        continueBtn.click();
    }

}