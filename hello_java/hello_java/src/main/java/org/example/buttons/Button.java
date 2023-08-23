package org.example.buttons;

public class Button implements IDoubleClickable{
   private final IClickable clickHandler;

    public Button(IClickable onClick){
        clickHandler=onClick;
    }

    public Button(IDoubleClickable onClick){
        clickHandler=onClick;
    }
    @Override
    public void click() throws Exception {
        if(clickHandler != null)
            clickHandler.click();
    }

    @Override
    public void doubleClick() {
        if(clickHandler instanceof IDoubleClickable && clickHandler != null){
            ((IDoubleClickable) clickHandler).doubleClick();
        }
    }
}
