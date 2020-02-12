package com.ngoding.sqlitecrud;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Inventory")
public class Inventory extends Model {

//    @Column(name = "id")
//    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "stock")
    public int stock;


}