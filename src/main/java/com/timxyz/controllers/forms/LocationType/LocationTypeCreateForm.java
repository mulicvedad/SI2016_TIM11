package com.timxyz.controllers.forms.LocationType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by amina on 06.05.2017..
 */
public class LocationTypeCreateForm {
    @Size(min = 4, max = 255) @NotNull
    private String name;

    @Size(min = 4, max = 255) @NotNull
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
