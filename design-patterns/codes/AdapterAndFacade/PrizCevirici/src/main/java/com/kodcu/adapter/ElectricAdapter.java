package com.kodcu.adapter;


public class ElectricAdapter implements TRElectric {

    USElecStandard usElecStandard;

    public ElectricAdapter(USElecStandard usElecStandard) {
        this.usElecStandard = usElecStandard;
    }
    @Override
    public void calis() {
        usElecStandard.work();
    }
}
