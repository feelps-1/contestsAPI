package hacka.mangue.contests.domain.models;

public enum Scholarity{
    ENS_FUND("Ensino Fundamental", ""),
    ENS_MED("Ensino Médio", "g"),
    GRAD("Graduação", ""),
    ENS_TEC("Ensino Técnico", ""),
    LIVRE("Livre", "");

    private final String name;
    private final String icon;

    Scholarity(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
