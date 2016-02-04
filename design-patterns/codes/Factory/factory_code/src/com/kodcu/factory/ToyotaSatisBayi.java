package com.kodcu.factory;

public class ToyotaSatisBayi {
    ToyotaAdapazariFabrikasi factory;

    public ToyotaSatisBayi(ToyotaAdapazariFabrikasi factory) {

        this.factory = factory;
    }

    public Araba arabaSiparisEt(String type) {
        Araba araba;

        araba = factory.arabaUret(type);

        araba.krediIslemleri();
        araba.sigortaIslemleri();
        araba.plakaIslemleri();
        araba.teslimIslemleri();

        return araba;
    }

}
