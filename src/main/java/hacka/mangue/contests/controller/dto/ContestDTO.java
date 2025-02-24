package hacka.mangue.contests.controller.dto;

import hacka.mangue.contests.domain.models.Contest;
import hacka.mangue.contests.domain.models.Scholarity;
import hacka.mangue.contests.domain.models.Tematic;

import java.util.List;
import java.util.stream.Collectors;

public class ContestDTO {

    private Long id;
    private String name;
    private String description;
    private List<String> tematic;
    private Long participants;
    private List<String> scholarity;
    private String icon;
    private String url;

    // Default constructor
    public ContestDTO() {
    }

    public ContestDTO(Contest contest) {
        this.id = contest.getId();
        this.name = contest.getName();
        this.description = contest.getDescription();
        this.tematic = convertEnumListToStringList(contest.getTematic());
        this.scholarity = convertEnumListToStringList(contest.getScholarity());
        this.participants = contest.getParticipants();
        this.icon = contest.getIcon();
        this.url = contest.getUrl();
    }

    public Contest toModel() {
        Contest contest = new Contest();
        contest.setId(this.id);
        contest.setName(this.name);
        contest.setDescription(this.description);
        contest.setTematic(convertStringListToEnumList(this.tematic, Tematic.class));
        contest.setScholarity(convertStringListToEnumList(this.scholarity, Scholarity.class));
        contest.setParticipants(this.participants);
        contest.setIcon(this.icon);
        contest.setUrl(this.url);
        return contest;
    }

    // Métodos auxiliares para conversão de listas de Enum
    private <E extends Enum<E>> List<String> convertEnumListToStringList(List<E> enumList) {
        return (enumList != null) ? enumList.stream().map(Enum::name).collect(Collectors.toList()) : List.of();
    }

    private <E extends Enum<E>> List<E> convertStringListToEnumList(List<String> stringList, Class<E> enumClass) {
        return (stringList != null) ? stringList.stream().map(value -> Enum.valueOf(enumClass, value)).collect(Collectors.toList()) : null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getTematic() {
        return tematic;
    }

    public void setTematic(List<String> tematic) {
        this.tematic = tematic;
    }

    public Long getParticipants() {
        return participants;
    }

    public void setParticipants(Long participants) {
        this.participants = participants;
    }

    public List<String> getScholarity() {
        return scholarity;
    }

    public void setScholarity(List<String> scholarity) {
        this.scholarity = scholarity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
