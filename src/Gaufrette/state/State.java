package Gaufrette.state;


public interface State {

    public void update();

    public void render();

    public void onEnter(Object param);

    public void onExit();
}
