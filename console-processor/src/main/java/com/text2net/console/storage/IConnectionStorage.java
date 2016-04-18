package com.text2net.console.storage;

import com.text2net.console.ConnectionElement;

/**
 * Created by rafa on 03/04/2016.
 */
public interface IConnectionStorage {
    void add(ConnectionElement elementA, ConnectionElement elementB, long inicioPortaria, String textoPortaria);
}
