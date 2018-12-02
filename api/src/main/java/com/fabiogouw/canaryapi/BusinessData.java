package com.fabiogouw.canaryapi;

public class BusinessData {

    private String _aaa;
    public String getAAA() {
        return _aaa;
    }
    public void setAAA(String aaa) {
        _aaa = aaa;
    }

    private String _bbb;
    public String getBBB() {
        return _bbb;
    }
    public void setBBB(String bbb) {
        _bbb = bbb;
    }

    private String _ccc;
    public String getCCC() {
        return _ccc;
    }
    public void setCCC(String ccc) {
        _ccc = ccc;
    }

    private String _ddd;
    public String getDDD() {
        return _ddd;
    }
    public void setDDD(String ddd) {
        _ddd = ddd;
    }

    private String _eee;
    public String getEEE() {
        return _eee;
    }
    public void setEEE(String eee) {
        _eee = eee;
    }

    private String _canary;
    public String getCanary() {
        return _canary;
    }
    public void setCanary(String canary) {
        _canary = canary;
    }

    @Override
    public String toString() {
        return _aaa + " " + _bbb + " " + _ccc + " " + _ddd + " " + _eee + " " + _canary;
    }
}