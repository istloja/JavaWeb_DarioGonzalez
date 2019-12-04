package Beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "primerBean")
@ViewScoped

public class primerBean implements Serializable{

    private int valorUno;

    public int getValorUno() {
        return valorUno;
    }

    public void setValorUno(int valorUno) {
        this.valorUno = valorUno;
    }
    
    public primerBean() {
    }
    
}
