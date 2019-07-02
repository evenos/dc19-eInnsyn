package com.example.demo.domain.rdf;

import com.complexible.pinto.Identifiable;
import com.complexible.pinto.annotations.RdfProperty;
import com.complexible.pinto.annotations.RdfsClass;
import org.openrdf.model.Resource;
import org.openrdf.model.impl.SimpleValueFactory;

@RdfsClass("arkiv:Journalpost")
public class Journalpost implements Identifiable {

    public Journalpost(Resource id, int registreringsID, String offentligTittel, String offentligTittelSensitiv, String offentligTittelList) {
        this.id = id;
        this.registreringsID = registreringsID;
        this.offentligTittel = offentligTittel;
        this.offentligTittelSensitiv = offentligTittelSensitiv;
        this.offentligTittelList = offentligTittelList;
    }

    public Journalpost(String id, int registreringsID, String offentligTittel, String offentligTittelSensitiv, String offentligTittelList) {
        this(SimpleValueFactory.getInstance().createIRI(id),
                registreringsID,
                offentligTittel,
                offentligTittelSensitiv,
                offentligTittelList);
    }

    private Resource id;

    private int registreringsID;
    private String offentligTittel;
    private String offentligTittelSensitiv;
    private String offentligTittelList;


    @RdfProperty("arkiv:registreringsID")
    public int getRegistreringsID() {
        return registreringsID;
    }

    public void setRegistreringsID(int registreringsID) {
        this.registreringsID = registreringsID;
    }

    @RdfProperty("arkiv:offentligTittel")
    public String getOffentligTittel() {
        return offentligTittel;
    }

    public void setOffentligTittel(String offentligTittel) {
        this.offentligTittel = offentligTittel;
    }


    @RdfProperty("arkiv:offentligTittelSensitiv")
    public String getOffentligTittelSensitiv() {
        return offentligTittelSensitiv;
    }

    public void setOffentligTittelSensitiv(String offentligTittelSensitiv) {
        this.offentligTittelSensitiv = offentligTittelSensitiv;
    }

    @RdfProperty("arkiv:offentligTittelList")
    public String getOffentligTittelList() {
        return offentligTittelList;
    }

    public void setOffentligTittelList(String offentligTittelList) {
        this.offentligTittelList = offentligTittelList;
    }

    @Override
    public Resource id() {
        return id;
    }

    @Override
    public void id(Resource id) {
        this.id = id;
    }
}
