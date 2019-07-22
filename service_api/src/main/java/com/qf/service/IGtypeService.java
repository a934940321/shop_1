package com.qf.service;

import com.qf.entity.Gtype;

import java.util.List;

public interface IGtypeService {

    List<Gtype> toList();

    int addGtype(Gtype gtype);

    boolean deleteGtypeById(Integer id);

    Gtype listById(Integer id);

    boolean update(Gtype gtype);

    List<Gtype> getSecondType();

    List<Gtype> getFirstTypes();
}
