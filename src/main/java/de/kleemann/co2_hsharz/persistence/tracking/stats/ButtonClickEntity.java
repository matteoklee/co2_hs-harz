package de.kleemann.co2_hsharz.persistence.tracking.stats;

import jakarta.persistence.Entity;

/**
 * Class "ButtonClickEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
public class ButtonClickEntity extends StatisticEntity {

    private String buttonClickEntityName;
    private int buttonClickEntityAmount;

    public ButtonClickEntity() {}

    public ButtonClickEntity(long id) {
        setStatisticEntityId(id);
    }

    public String getButtonClickEntityName() {
        return buttonClickEntityName;
    }

    public void setButtonClickEntityName(String buttonClickEntityName) {
        this.buttonClickEntityName = buttonClickEntityName;
    }

    public int getButtonClickEntityAmount() {
        return buttonClickEntityAmount;
    }

    public void setButtonClickEntityAmount(int buttonClickEntityAmount) {
        this.buttonClickEntityAmount = buttonClickEntityAmount;
    }
}
