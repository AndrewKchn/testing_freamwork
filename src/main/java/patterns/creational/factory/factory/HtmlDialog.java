package patterns.creational.factory.factory;

import patterns.creational.factory.buttons.Button;
import patterns.creational.factory.buttons.HtmlButton;

public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
