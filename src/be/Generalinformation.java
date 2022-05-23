package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Generalinformation {

    private final StringProperty mastery = new SimpleStringProperty();
    private final StringProperty motivation = new SimpleStringProperty();
    private final StringProperty resources = new SimpleStringProperty();
    private final StringProperty roles = new SimpleStringProperty();
    private final StringProperty habits = new SimpleStringProperty();
    private final StringProperty education = new SimpleStringProperty();
    private final StringProperty lifeStory = new SimpleStringProperty();
    private final StringProperty network = new SimpleStringProperty();
    private final StringProperty healthInformation = new SimpleStringProperty();
    private final StringProperty assistiveDevices = new SimpleStringProperty();
    private final StringProperty homeDecor = new SimpleStringProperty();

    public Generalinformation() {
    }

    public StringProperty getMasteryProperty() {
        return mastery;
    }

    public void setMastery(String mastery) {
        this.mastery.set(mastery);
    }

    public StringProperty getMotivationProperty() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation.set(motivation);
    }

    public StringProperty getResourcesProperty() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources.set(resources);
    }

    public StringProperty getRolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public StringProperty getHabitsProperty() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits.set(habits);
    }

    public StringProperty getEducationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public StringProperty getLifeStoryProperty() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory.set(lifeStory);
    }

    public StringProperty getNetworkProperty() {
        return network;
    }

    public void setNetwork(String network) {
        this.network.set(network);
    }

    public StringProperty getHealthInformationProperty() {
        return healthInformation;
    }

    public void setHealthInformation(String healthInformation) {
        this.healthInformation.set(healthInformation);
    }
    public StringProperty getAssistiveDevicesProperty() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices.set(assistiveDevices);
    }

    public StringProperty getHomeDecorProperty() {
        return homeDecor;
    }

    public void setHomeDecor(String homeDecor) {
        this.homeDecor.set(homeDecor);
    }
}
