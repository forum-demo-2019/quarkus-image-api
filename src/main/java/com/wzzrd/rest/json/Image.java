package org.acme.rest.json;

import java.util.Objects;

public class Image {

    private String name;

    public Image(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
