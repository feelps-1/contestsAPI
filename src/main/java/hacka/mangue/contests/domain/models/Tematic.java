package hacka.mangue.contests.domain.models;

public enum Tematic{
    EMPR("Empreendedorismo", ""),
    SOCIAL("Impacto Social", ""),
    TECH("Tecnologia", ""),
    FINANCE("Finanças", ""),
    LIVRE("Livre", ""),
    AMBIENTE("Sustentabilidade", "");

    private final String name;
    private final String icon;

    Tematic(String name, String icon) {
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
