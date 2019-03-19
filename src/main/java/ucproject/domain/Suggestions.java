package ucproject.domain;

import java.util.ArrayList;
import java.util.List;

public class Suggestions {
    List<INNOrganization> suggestions = new ArrayList<>();

    public List<INNOrganization> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<INNOrganization> suggestions) {
        this.suggestions = suggestions;
    }
}
