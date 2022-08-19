package com.sukhoev.psms.rack.serviceСlass;

public class Load {

    private String connectionName;
    private int maxLoad;
    private int reserveLoad;

    public Load(String connectionName, int maxLoad, int reserveLoad) {
        this.connectionName = connectionName;
        this.maxLoad = maxLoad;
        this.reserveLoad = reserveLoad;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getReserveLoad() {
        return reserveLoad;
    }

    public void setReserveLoad(int reserveLoad) {
        this.reserveLoad = reserveLoad;
    }
}
