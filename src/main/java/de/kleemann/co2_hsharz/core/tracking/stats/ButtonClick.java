package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import lombok.NonNull;

/**
 * Class "ButtonClick" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class ButtonClick extends StatisticImpl {

    private ButtonClickEntity buttonClickEntity;

    public ButtonClick(@NonNull ButtonClickEntity buttonClickEntity){
        super(buttonClickEntity);
        this.buttonClickEntity = buttonClickEntity;
    }

    public String getButtonClickName(){
        return this.buttonClickEntity.getButtonClickEntityName();
    }

    public void setButtonClickName(String buttonClickName){
        this.buttonClickEntity.setButtonClickEntityName(buttonClickName);
    }

    private int getButtonClickAmount(){
        return this.buttonClickEntity.getButtonClickEntityAmount();
    }

    public void setButtonClickAmount(int buttonClickAmount){
        this.buttonClickEntity.setButtonClickEntityAmount(buttonClickAmount);
    }
}
