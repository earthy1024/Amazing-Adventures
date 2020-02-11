package student.adventure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "description",
        "items",
        "directions"
})
public class Rooms {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("items")
    private List<String> items = null;
    @JsonProperty("directions")
    private List<Directions> directions = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Rooms() {
    }

    /**
     *
     * @param directions
     * @param name
     * @param description
     * @param items
     */
    public Rooms(String name, String description, List<String> items, List<Directions> directions) {
        super();
        this.name = name;
        this.description = description;
        this.items = items;
        this.directions = directions;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Rooms withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Rooms withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("items")
    public List<String> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<String> items) {
        this.items = items;
    }

    public Rooms withItems(List<String> items) {
        this.items = items;
        return this;
    }

    @JsonProperty("directions")
    public List<Directions> getDirections() {
        return directions;
    }

    @JsonProperty("directions")
    public void setDirections(List<Directions> directions) {
        this.directions = directions;
    }

    public Rooms withDirections(List<Directions> directions) {
        this.directions = directions;
        return this;
    }

}
