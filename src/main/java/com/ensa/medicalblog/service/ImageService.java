package com.ensa.medicalblog.service;

import jakarta.servlet.http.Part;

import java.io.IOException;

public interface ImageService {
    String uploadFile(Part file) throws IOException;
}
