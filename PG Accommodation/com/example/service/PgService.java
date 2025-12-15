package com.example.service;

import com.example.entity.*;
import java.util.List;

public interface PgService {

    List<PgPlace> getPgByCity(Long cityId);

    List<PgPlace> getPgByLocality(String locality);

    PgPlace getPgDetails(Long id);

    Owner getOwnerDetails(Long pgId);
}
