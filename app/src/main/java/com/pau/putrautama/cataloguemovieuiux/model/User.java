package com.pau.putrautama.cataloguemovieuiux.model;

public class User {
    private String namaLengkap;
    private String noTlp;
    private String email;
    private String password;


    public User(String namaLengkap, String noTlp, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.noTlp = noTlp;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoTlp() {
        return noTlp;
    }

    public void setNoTlp(String noTlp) {
        this.noTlp = noTlp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
