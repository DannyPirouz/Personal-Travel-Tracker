package persistance;

import org.json.JSONObject;

// Attributing source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {

    // Modifies: this
    // Effects: returns this as JSON object
    JSONObject toJson();
}
