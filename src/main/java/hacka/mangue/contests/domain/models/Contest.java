package hacka.mangue.contests.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_contests")
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Tematic> tematic;

    private Long participants;

    @ManyToMany
    private List<Scholarity> scholarity;

    private String icon;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Scholarity> getScholarity() {
        return scholarity;
    }

    public void setScholarity(List<Scholarity> scholarity) {
        this.scholarity = scholarity;
    }

    public Long getParticipants() {
        return participants;
    }

    public void setParticipants(Long participants) {
        this.participants = participants;
    }

    public List<Tematic> getTematic() {
        return tematic;
    }

    public void setTematic(List<Tematic> tematic) {
        this.tematic = tematic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
