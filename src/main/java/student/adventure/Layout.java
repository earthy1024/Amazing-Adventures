package student.adventure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "startingRoom",
        "endingRoom",
        "rooms"
})
public class Layout {

    @JsonProperty("startingRoom")
    private String startingRoom;
    @JsonProperty("endingRoom")
    private String endingRoom;
    @JsonProperty("rooms")
    private List<Rooms> rooms = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Layout() {
    }

    /**
     *
     * @param startingRoom
     * @param rooms
     * @param endingRoom
     */
    public Layout(String startingRoom, String endingRoom, List<Rooms> rooms) {
        super();
        this.startingRoom = startingRoom;
        this.endingRoom = endingRoom;
        this.rooms = rooms;
    }

    @JsonProperty("startingRoom")
    public String getStartingRoom() {
        return startingRoom;
    }

    @JsonProperty("startingRoom")
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public Layout withStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
        return this;
    }

    @JsonProperty("endingRoom")
    public String getEndingRoom() {
        return endingRoom;
    }

    @JsonProperty("endingRoom")
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public Layout withEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
        return this;
    }

    @JsonProperty("rooms")
    public List<Rooms> getRooms() {
        return rooms;
    }

    @JsonProperty("rooms")
    public void setRooms(List<Rooms> rooms) {
        this.rooms = rooms;
    }

    public Layout withRooms(List<Rooms> rooms) {
        this.rooms = rooms;
        return this;
    }

}
