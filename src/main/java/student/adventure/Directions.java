package student.adventure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "directionName",
        "room"
})
public class Directions {

    @JsonProperty("directionName")
    private String directionName;
    @JsonProperty("room")
    private String room;

    /**
     * No args constructor for use in serialization
     *
     */
    public Directions() {
    }

    /**
     *
     * @param directionName
     * @param room
     */
    public Directions(String directionName, String room) {
        super();
        this.directionName = directionName;
        this.room = room;
    }

    @JsonProperty("directionName")
    public String getDirectionName() {
        return directionName;
    }

    @JsonProperty("directionName")
    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Directions withDirectionName(String directionName) {
        this.directionName = directionName;
        return this;
    }

    @JsonProperty("room")
    public String getRoom() {
        return room;
    }

    @JsonProperty("room")
    public void setRoom(String room) {
        this.room = room;
    }

    public Directions withRoom(String room) {
        this.room = room;
        return this;
    }

}