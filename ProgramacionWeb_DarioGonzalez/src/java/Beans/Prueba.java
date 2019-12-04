
package Beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "prueba")
@Dependent
public class Prueba {

    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public Prueba() {
    }
    
}
