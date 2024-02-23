package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import lombok.NonNull;

/**
 * This Class is a specialization of the {@link StatisticImpl} and tracks button clicks in the frontend
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class ButtonClick extends StatisticImpl {

	/** {@link ButtonClickEntity} used to create this Object */
    private ButtonClickEntity buttonClickEntity;

    /**
     * Constructs a new {@link ButtonClick} Object
     * @param buttonClickEntity {@link ButtonClickEntity} used to create this object
     */
    public ButtonClick(@NonNull ButtonClickEntity buttonClickEntity){
        super(buttonClickEntity);
        this.buttonClickEntity = buttonClickEntity;
    }

    /**
     * Returns the name of the button
     * @return {@link String} name of the button
     */
    public String getButtonClickName(){
        return this.buttonClickEntity.getButtonClickEntityName();
    }

    /**
     * Sets the name of the button
     * @param buttonClickName {@link String} new name
     */
    public void setButtonClickName(String buttonClickName){
        this.buttonClickEntity.setButtonClickEntityName(buttonClickName);
    }

    /**
     * Returns the amount of times this button was clicked
     * @return {@link Integer} button click amount
     */
    public int getButtonClickAmount(){
        return this.buttonClickEntity.getButtonClickEntityAmount();
    }

    /**
     * Sets the amount of times this button was clicked
     * @param buttonClickAmount {@link Integer} new amount
     */
    public void setButtonClickAmount(int buttonClickAmount){
        this.buttonClickEntity.setButtonClickEntityAmount(buttonClickAmount);
    }
}
