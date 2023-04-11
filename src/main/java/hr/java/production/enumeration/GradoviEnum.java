package hr.java.production.enumeration;

public enum GradoviEnum {
    ZAGREB("Zagreb", "10000"),
    RIJEKA("Rijeka", "51000"),
    GRADISKA("Gradiska", "35400"),
    KUTINA("Kutina", "44320");
    private String name, postalCode;

    GradoviEnum(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
