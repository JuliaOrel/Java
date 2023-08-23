package org.example.buttons;

public class Button implements IClickable{
   private final IClickable clickHandler;

    public Button(IClickable onClick){
        clickHandler=onClick;
    }
    @Override
    public void click() {
        if(clickHandler != null)
            clickHandler.click();
    }
}
