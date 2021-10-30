package co.usa.ciclo3.reto5.custom;

import co.usa.ciclo3.reto5.model.Client;

public class CountClientes {
    private Long total;
    private Client client;
    public CountClientes(Long total, Client client) {
        this.total = total;
        this.client = client;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    
}
